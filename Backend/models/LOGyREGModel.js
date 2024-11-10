const pool = require('../dbConfig');

// Registrar un nuevo usuario
exports.registerUser = async (userData) => {
    const { username, email, password, rol } = userData;

    const query = `
        INSERT INTO usuarios (username, email, password, rol)
        VALUES ($1, $2, $3, $4)
        RETURNING id_usuario
    `;
    console.log("Registrando usuario:", userData);
    const result = await pool.query(query, [username, email, password, rol]);
    console.log("Usuario registrado con ID:", result.rows[0].id_usuario);
    return result.rows[0].id_usuario;
};

// Login de usuario
exports.login = async ({ email, password }) => {
    try {
        console.log("Iniciando sesión para el usuario con email:", email); // Log para el inicio de sesión

        const query = `
            SELECT id_usuario, username, email, rol, password
            FROM usuarios
            WHERE email = $1
        `;
        const result = await pool.query(query, [email]);

        console.log("Resultado de la consulta:", result.rows); // Log para ver el resultado de la consulta

        if (result.rows.length === 0) {
            console.log("Login fallido: usuario no encontrado.");
            return { error: "Usuario no encontrado" };
        }

        const user = result.rows[0];
        console.log("Usuario encontrado:", user); // Log para mostrar el usuario encontrado

        // Comparar la contraseña proporcionada con la almacenada (sin cifrado)
        console.log("Contraseña proporcionada:", password); // Log para mostrar la contraseña proporcionada
        
        if (password !== user.password) {
            console.log("Login fallido: contraseña incorrecta.");
            return { error: "Contraseña incorrecta" };
        }

        console.log("Login exitoso para el usuario:", user);
        // Retorna todos los datos del usuario, excluyendo la contraseña
        const { password: userPassword, ...userData } = user; // Excluye la contraseña
        return userData; // Retorna el resto de los datos del usuario
    } catch (error) {
        console.error("Error al iniciar sesión:", error.message); // Log del error en la consola
        return { message: "Error al iniciar sesión", error: error.message }; // Retorna el error
    }
};
