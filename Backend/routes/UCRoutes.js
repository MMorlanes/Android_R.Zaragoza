const express = require('express');
const router = express.Router();
const UCController = require('../controllers/UCController'); 

// Ruta para listar los 10 propietarios con más ventas
router.get('/top-sellers', UCController.listTopSellers);

// Ruta para puntuar un producto
router.post('/rate', UCController.rateProduct);

// Ruta para obtener los 10 productos más puntuados
router.get('/top-rated', UCController.listTopRatedProducts);

// Ruta para buscar productos por nombre
router.get('/search', UCController.listProducts);

// Ruta para listar productos por nombre de categoría
router.get('/categories/:category', UCController.listProductsByCategory);

// Ruta para ver los detalles de un producto específico
router.get('/:id', UCController.getProductDetails);

// Ruta para ver el historial de un usuario
router.get('/history/:id_usuario', (req, res) => {
    console.log(req.params.id_usuario);  // Verifica si el parámetro se está capturando correctamente
    UCController.purchaseHistory(req, res);
});

// Ruta para confirmar compra
router.post('/purchase', UCController.confirmPurchase);

module.exports = router;