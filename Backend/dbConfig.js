const { Pool } = require('pg');

const pool = new Pool({
    user: 'postgres',
    host: 'postgres.cfokmaiqaowb.us-east-1.rds.amazonaws.com',
    database: '',
    password: 'aABbzZ13579',
    port: 5432,
    ssl: {
        rejectUnauthorized: false 
    }
});

// Probar la conexión
pool.connect((err, client, release) => {
    if (err) {
        console.error('Error al conectar a la base de datos', err.stack);
    } else {
        console.log('Conexión exitosa a la base de datos en el puerto 5432');
    }
    release();
});

module.exports = pool;
