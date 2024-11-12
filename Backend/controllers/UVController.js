const UVModel = require('../models/UVModel');

// Agregar un nuevo producto
exports.addProduct = async (req, res) => {
    try {
        const { id_usuario } = req.body;

        if (!id_usuario) {
            return res.status(400).json({ message: "El campo id_usuario es obligatorio" });
        }

        const result = await UVModel.createProduct(req.body);
        res.status(201).json({ message: "Producto agregado exitosamente", id_producto: result });
    } catch (error) {
        res.status(500).json({ message: "Error al agregar producto", error });
    }
};

// Listar los productos de un vendedor específico
exports.listMyProducts = async (req, res) => {
    try {
        const id_usuario = req.params.id_usuario;
        console.log(`Listando productos para el vendedor con ID: ${id_usuario}`);

        if (!id_usuario) {
            return res.status(400).json({ message: "El ID del usuario es obligatorio" });
        }

        const productos = await UVModel.getProductsBySeller(id_usuario);
        res.status(200).json(productos);
    } catch (error) {
        console.error("Error al listar productos del vendedor:", error);
        res.status(500).json({ message: "Error al listar productos del vendedor", error });
    }
};
