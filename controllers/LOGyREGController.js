const LOGyREGModel = require('../models/LOGyREGModel');

// Registrar un nuevo usuario
exports.register = async (req, res) => {
    try {
        const result = await LOGyREGModel.registerUser(req.body);
        res.status(201).json({ message: 'Usuario registrado exitosamente', userId: result });
    } catch (error) {
        res.status(500).json({ message: 'Error al registrar el usuario', error });
    }
};

// Login de usuario
exports.login = async (req, res) => {
    try {
        const user = await LOGyREGModel.login(req.body);
        if (user.error) {
            return res.status(401).json(user); // Maneja el error de login
        }
        res.status(200).json({ message: 'Login exitoso', user });
    } catch (error) {
        res.status(500).json({ message: 'Error al iniciar sesión', error });
    }
};

