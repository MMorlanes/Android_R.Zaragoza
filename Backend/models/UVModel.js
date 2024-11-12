const pool = require('../dbConfig');

// Agregar un nuevo producto
exports.createProduct = async (productData) => {
    const { id_usuario, nombre_producto, desc_prod, imagen_prod, precio, categoria } = productData;

    try {
        // Inserción del nuevo producto
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
exports.getProductsBySeller = async (id_usuario) => {
    const query = `
        SELECT p.id_producto, p.nombre_producto, p.desc_prod, p.precio, p.imagen_prod, p.categoria
        FROM productos p
        WHERE p.id_usuario = $1
    `;
    console.log("Consultando productos del vendedor con ID:", id_usuario);

    try {
        const result = await pool.query(query, [id_usuario]);

        console.log("Productos encontrados del vendedor:", result.rows.length);
        return result.rows;
    } catch (error) {
        console.error("Error al consultar productos del vendedor:", error.message);
        throw error;
    }
};

