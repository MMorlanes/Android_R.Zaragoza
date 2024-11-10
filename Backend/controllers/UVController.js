const UVModel = require('../models/UVModel');

// Agregar un nuevo producto
exports.addProduct = async (req, res) => {
    try {
        const result = await UVModel.createProduct(req.body);
        res.status(201).json({ message: 'Producto agregado exitosamente', id_producto: result });
    } catch (error) {
        res.status(500).json({ message: 'Error al agregar producto', error });
    }
};

// Listar los productos de un vendedor específico
exports.listMyProducts = async (req, res) => {
    try {
        const email = req.params.email;
        console.log(`Listando productos para el vendedor ID: ${email}`);
        const productos = await UVModel.getProductsBySeller(email);
        res.status(200).json(productos);
    } catch (error) {
        console.error("Error al listar productos del vendedor:", error);
        res.status(500).json({ message: 'Error al listar productos del vendedor', error });
    }
};