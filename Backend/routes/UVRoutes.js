const express = require('express');
const router = express.Router();
const UVController = require('../controllers/UVController');

// Ruta para agregar un nuevo producto
router.post('/addProducts', UVController.addProduct); 

// Ruta para listar los productos de un vendedor específico
router.get('/seller/:id_usuario', UVController.listMyProducts);

module.exports = router;