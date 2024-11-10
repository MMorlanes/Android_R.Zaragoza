const express = require('express');
const router = express.Router();
const LOGyREGController = require('../controllers/LOGyREGController');

// Ruta para registrar un nuevo usuario
router.post('/register', LOGyREGController.register);

// Ruta para login
router.post('/login', LOGyREGController.login);

module.exports = router;
