const UCModel = require('../models/UCModel');

// Listar los 10 propietarios con más ventas
exports.listTopSellers = async (req, res) => {
    try {
        const sellers = await UCModel.getTop10Vendedores();
        res.status(200).json(sellers);
    } catch (error) {
        res.status(500).json({ message: 'Error al listar los mejores vendedores', error });
    }
};

// Puntuar un producto
exports.rateProduct = async (req, res) => {
    console.log("Datos recibidos para puntuar el producto:", req.body);
    
    try {
        await UCModel.rateProduct(req.body);
        console.log("Producto puntuado exitosamente.");
        res.status(200).json({ message: 'Producto puntuado exitosamente' });
    } catch (error) {
        console.error("Error al puntuar producto:", error);
        res.status(500).json({ message: 'Error al puntuar producto', error });
    }
};

// Listar los 10 productos más puntuados
exports.listTopRatedProducts = async (req, res) => {
    try {
        const products = await UCModel.getTop10Prod();
        res.status(200).json(products);
    } catch (error) {
        res.status(500).json({ message: 'Error al listar productos más puntuados', error });
    }
};

// Listar productos por nombre de categoría
exports.listProductsByCategory = async (req, res) => {
    try {
        const category = req.params.category; // Obtiene la categoría desde los parámetros de la ruta
        const products = await UCModel.getProductsByCategory(category); // Llama al modelo para obtener los productos
        res.status(200).json(products); // Devuelve los productos en formato JSON
    } catch (error) {
        res.status(500).json({ message: 'Error al listar productos por categoría', error });
    }
};

// Listar productos con filtros (por nombre de categoría y búsqueda)
exports.listProducts = async (req, res) => {
    try {
        console.log("Parámetros recibidos:", req.query); // Ver los parámetros recibidos
        const products = await UCModel.getProducts(req.query); // Llamar al modelo para obtener los productos
        console.log("Productos encontrados:", products.length); // Mostrar el número de productos encontrados
        res.status(200).json(products); // Enviar los productos como respuesta
    } catch (error) {
        console.error("Error al listar productos:", error);
        res.status(500).json({ message: 'Error al listar productos', error });
    }
};

// Ver detalles de un producto específico
exports.getProductDetails = async (req, res) => {
    try {
        const product = await UCModel.getProductDetails(req.params.id);
        if (product) {
            res.status(200).json(product);
        } else {
            res.status(404).json({ message: 'Producto no encontrado' });
        }
    } catch (error) {
        res.status(500).json({ message: 'Error al obtener detalles del producto', error });
    }
};

// Confirmar compra
exports.confirmPurchase = async (req, res) => {
    try {
        const result = await UCModel.confirmPurchase(req.body);
        res.status(200).json({ message: 'Compra confirmada exitosamente', purchaseId: result });
    } catch (error) {
        res.status(500).json({ message: 'Error al confirmar la compra', error });
    }
};

// Ver historial de compras del usuario
exports.purchaseHistory = async (req, res) => {
    try {
        // Asegúrate de que el nombre del parámetro sea correcto
        const history = await UCModel.getPurchaseHistory(req.params.id_usuario);
        res.status(200).json(history);
    } catch (error) {
        res.status(500).json({ message: 'Error al obtener el historial de compras', error });
    }
};
