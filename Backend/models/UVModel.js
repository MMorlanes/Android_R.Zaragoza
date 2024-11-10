const pool = require('../dbConfig');

// Agregar un nuevo producto
exports.createProduct = async (productData) => {
    const { email, nombre_producto, desc_prod, imagen_prod, precio, categoria } = productData;

    try {
        // Obtener el id_usuario a partir del email
        const userQuery = `
            SELECT id_usuario FROM Usuarios WHERE email = $1
        `;
        const userResult = await pool.query(userQuery, [email]);

        if (userResult.rows.length === 0) {
            console.log("Usuario no encontrado con el email:", email);
            return { error: "Usuario no encontrado" };
        }

        const id_usuario = userResult.rows[0].id_usuario;
        console.log("ID de usuario obtenido:", id_usuario);

        // Inserción del nuevo producto con el id_usuario obtenido
        const productQuery = `
            INSERT INTO productos (id_usuario, nombre_producto, desc_prod, imagen_prod, precio, categoria)
            VALUES ($1, $2, $3, $4, $5, $6)
            RETURNING id_producto
        `;
        const result = await pool.query(productQuery, [id_usuario, nombre_producto, desc_prod, imagen_prod, precio, categoria]);

        console.log("Producto creado con ID:", result.rows[0].id_producto);
        return result.rows[0].id_producto;

    } catch (error) {
        console.error("Error al crear el producto:", error.message);
        return { error: "Error al crear el producto", details: error.message };
    }
};


// Listar productos por vendedor
exports.getProductsBySeller = async (email) => {
    const query = `
        SELECT p.id_producto, p.nombre_producto, p.desc_prod, p.precio, p.imagen_prod, p.categoria
        FROM productos p
        JOIN usuarios u ON p.id_usuario = u.id_usuario
        WHERE u.email = $1
    `;
    console.log("Consultando productos del vendedor con email:", email);

    const result = await pool.query(query, [email]);

    console.log("Productos encontrados del vendedor:", result.rows.length);
    return result.rows;
};
