const pool = require('../dbConfig');

// Listar los 10 propietarios con más ventas
exports.getTop10Vendedores = async () => {
    const query = `
        SELECT u.id_usuario, u.username, COUNT(v.id_venta) AS total_ventas
        FROM usuarios u
        JOIN ventas v ON u.id_usuario = v.id_usuario_vend
        WHERE v.estado = 'confirmada'
        GROUP BY u.id_usuario
        ORDER BY total_ventas DESC
        LIMIT 10
    `;
    console.log("Consultando los 10 propietarios con más ventas...");
    const result = await pool.query(query);
    console.log("Propietarios con más ventas:", result.rows);
    return result.rows;
};


// Puntuar un producto
exports.rateProduct = async ({ id_producto, id_usuario, puntuacion, comentario }) => {
    console.log("Datos para puntuar el producto:", { id_producto, id_usuario, puntuacion, comentario });

    // Verificar si el usuario ya puntuó el producto
    const checkQuery = `
        SELECT COUNT(*) 
        FROM puntuaciones 
        WHERE id_producto = $1 AND id_usuario = $2
    `;
    const checkResult = await pool.query(checkQuery, [id_producto, id_usuario]);
    
    console.log("Verificando si el usuario ya puntuó el producto:", checkResult.rows[0].count);

    if (checkResult.rows[0].count > 0) {
        console.log("El usuario ya ha puntuado este producto.");
        throw new Error('Este producto ya ha sido puntuado por este usuario.');
    }

    // Insertar la puntuación del producto
    const insertQuery = `
        INSERT INTO puntuaciones (id_producto, id_usuario, puntuacion, comentario)
        VALUES ($1, $2, $3, $4)
    `;
    console.log("Insertando puntuación:", { id_producto, id_usuario, puntuacion, comentario });
    
    try {
        // Ejecutamos la consulta de inserción
        await pool.query(insertQuery, [id_producto, id_usuario, puntuacion, comentario]);
        console.log("Producto puntuado correctamente.");
    } catch (error) {
        console.error("Error al puntuar el producto:", error);
        throw new Error('No se pudo puntuar el producto.');
    }
};


// Obtener los 10 productos más puntuados
exports.getTop10Prod = async () => {
    const query = `
        SELECT p.id_producto, p.nombre_producto, p.desc_prod, p.precio, p.imagen_prod, AVG(pu.puntuacion) AS promedio_puntuacion
        FROM productos p
        JOIN puntuaciones pu ON p.id_producto = pu.id_producto
        GROUP BY p.id_producto
        ORDER BY promedio_puntuacion DESC
        LIMIT 10
    `;
    console.log("Consultando los 10 productos más puntuados...");
    const result = await pool.query(query);
    console.log("Productos más puntuados:", result.rows);
    return result.rows;
};


// Listar productos por nombre de categoría (Punto 6 del enunciado)
exports.getProductsByCategory = async (category) => {
    const query = `
        SELECT p.id_producto, p.nombre_producto, p.desc_prod, p.precio, p.imagen_prod, u.username AS vendedor
        FROM productos p
        JOIN usuarios u ON p.id_usuario = u.id_usuario
        WHERE p.categoria = $1
    `;
    console.log("Consultando productos por categoría:", category);
    const result = await pool.query(query, [category]);
    console.log("Productos encontrados en la categoría:", result.rows.length);
    return result.rows;
};


// Listar productos con filtros (por nombre de categoría y búsqueda)
exports.getProducts = async ({ categoria, search }) => {
    let query = `
        SELECT p.id_producto, p.nombre_producto, p.desc_prod, p.precio, p.imagen_prod, u.username AS vendedor
        FROM productos p
        JOIN usuarios u ON p.id_usuario = u.id_usuario
        WHERE p.precio > 0
    `;
    const params = [];

    // Filtrar por categoría si se pasa
    if (categoria) {
        query += ' AND p.categoria = $1';
        params.push(categoria);
    }

    // Filtrar por nombre del producto si se pasa
    if (search) {
        query += ` AND p.nombre_producto ILIKE $${params.length + 1}`;
        params.push(`%${search}%`);
    }

    console.log("Consultando productos con filtros:", { categoria, search });
    const result = await pool.query(query, params);
    console.log("Resultados de la consulta:", result.rows);
    return result.rows;
};

// Obtener detalles de un producto (Punto 8 del enunciado)
exports.getProductDetails = async (productId) => {
    const query = `
        SELECT 
            p.id_producto, 
            p.nombre_producto, 
            p.desc_prod, 
            p.precio, 
            p.imagen_prod, 
            u.username AS vendedor,
            p.categoria
        FROM productos p
        JOIN usuarios u ON p.id_usuario = u.id_usuario
        WHERE p.id_producto = $1
    `;
    console.log("Consultando detalles del producto con ID:", productId);
    const result = await pool.query(query, [productId]);
    console.log("Detalles del producto:", result.rows[0]);
    return result.rows[0];
};

// Confirmar compra (crear un pedido)
exports.confirmPurchase = async ({ id_usuario, producto_id }) => {
    const client = await pool.connect();
    try {
        // Verificar si el usuario es cliente
        const userQuery = `
            SELECT rol FROM usuarios WHERE id_usuario = $1
        `;
        const userResult = await client.query(userQuery, [id_usuario]);

        if (userResult.rows.length === 0 || userResult.rows[0].rol !== 'cliente') {
            throw new Error('Solo los usuarios con rol de cliente pueden confirmar compras');
        }

        await client.query('BEGIN');

        // Obtener el precio unitario del producto
        const productQuery = `
            SELECT precio FROM productos WHERE id_producto = $1
        `;
        const productResult = await client.query(productQuery, [producto_id]);
        if (productResult.rows.length === 0) throw new Error('Producto no encontrado');
        const precio_unitario = productResult.rows[0].precio;

        // Crear el detalle del pedido en Detalle_pedido
        const detallePedidoQuery = `
            INSERT INTO Detalle_pedido (id_usuario_pedido, precio_total)
            VALUES ($1, $2)
            RETURNING id_detalle_pedido
        `;
        const detallePedidoResult = await client.query(detallePedidoQuery, [id_usuario, precio_unitario]);
        const id_detalle_pedido = detallePedidoResult.rows[0].id_detalle_pedido;
        console.log("Detalle de pedido creado con ID:", id_detalle_pedido);

        // Insertar el producto en la tabla Pedido, incluyendo precio unitario
        const pedidoQuery = `
            INSERT INTO Pedido (id_detalle_pedido, id_usuario, id_producto, precio_unitario)
            VALUES ($1, $2, $3, $4)
        `;
        await client.query(pedidoQuery, [id_detalle_pedido, id_usuario, producto_id, precio_unitario]);
        console.log("Producto agregado al pedido con ID de detalle:", id_detalle_pedido);

        await client.query('COMMIT');
        return id_detalle_pedido;
    } catch (error) {
        await client.query('ROLLBACK');
        console.error("Error confirmando compra:", error);
        throw error;
    } finally {
        client.release();
    }
};



// Ver historial de compras (detalle_pedido) del usuario
exports.getPurchaseHistory = async (id_usuario) => {
    const query = `
        SELECT 
            dp.fecha_detalle_pedido AS fecha_compra,
            p.imagen_prod AS imagen_producto,
            p.nombre_producto,
            pe.cantidad,
            pe.precio_unitario,
            dp.precio_total
        FROM 
            Detalle_pedido dp
        JOIN 
            Pedido pe ON dp.id_detalle_pedido = pe.id_detalle_pedido
        JOIN 
            Productos p ON pe.id_producto = p.id_producto
        WHERE 
            dp.id_usuario_pedido = $1  -- Usamos el parámetro para el id del usuario
        ORDER BY 
            dp.fecha_detalle_pedido DESC  -- Ordenamos por la fecha de compra más reciente
    `;

    try {
        // Ejecutar la consulta usando el pool de conexión
        console.log("Consultando historial de compras para el usuario ID:", id_usuario);
        const result = await pool.query(query, [id_usuario]);
        console.log("Historial de compras encontrado:", result.rows);
        return result.rows;  // Retornamos el resultado
    } catch (error) {
        console.error("Error al consultar el historial de compras:", error);
        throw new Error('Error al consultar el historial de compras');
    }
};





