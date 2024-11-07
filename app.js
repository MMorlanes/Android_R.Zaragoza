const express = require('express');
const bodyParser = require('body-parser');
const LOGyREGRoutes = require('./routes/LOGyREGRoutes');
const UVRoutes = require('./routes/UVRoutes');
const UCRoutes = require('./routes/UCRoutes');

const app = express();
const PORT = process.env.PORT || 3000;

app.use(bodyParser.json());

// Configuración de rutas
app.use('/api/LOGyREG', LOGyREGRoutes); // Rutas para autenticación (login y registro de usuarios)
app.use('/api/UV', UVRoutes); // Rutas para gestión de productos (casos de uso)
app.use('/api/UC', UCRoutes); // Rutas para funcionalidades de usuario (casos de uso)

// Ruta de prueba
app.get('/', (req, res) => {
    res.send('Servidor en funcionamiento');
});

// Iniciar el servidor
app.listen(PORT, () => {
    console.log(`Servidor escuchando en el puerto ${PORT}`);
});


// RUTAS PARA PROBAR EN EL THUNDERCLIENT:

// LOGIN -> (POST) http://localhost:3000/api/LOGyREG/login
// BODY LOGIN:
//    {
//    "email": "vendedor3@example.com",
//    "password": "1234"
//     }
// REGISTRO (POST) http://localhost:3000/api/LOGyREG/register
// BODY REGISTRO:
//      {
    //  "username": "prueba01",
    //  "email": "prueba01@gmail.com",
    //  "password": "1234",
    //  "rol": "cliente"
    //   }

    // AGREGAR UN NUEVO PRODUCTO -> (POST) http://localhost:3000/api/UV/addProducts
// BODY AGREGAR UN NUEVO PRODUCTO:
// {
//     "email": "user2@example.com",
//     "nombre_producto": "prueba02",
//     "desc_prod": "Descripción del producto de prueba",
//     "imagen_prod": "ruta/imagen_prueba.jpg",
//     "precio": 19.99,
//     "categoria": "mujer"
// }

// LISTAR LOS PRODUCTOS DE UN VENDEDOR -> (GET) http://localhost:3000/api/UV/seller/user2@example.com
// TOP 10 VENDEDORES -> (GET) http://localhost:3000/api/UC/top-sellers
// PUNTUAR UN PRODUCTO -> (POST) http://localhost:3000/api/UC/rate
// BODY PUNTUAR PRODUCTO
// {
//     "id_producto": 2,
//     "email": "user1@example.com",
//     "puntuacion": 4,
//     "comentario": "Producto excelente"
//   }
// 10 PRODUCTOS MEJOR VALORADOS (SALEN 6 PRODUCTOS, AÑADIR MÁS DE 10 PARA VER SI VA CORRECTAMENTE) -> (GET) http://localhost:3000/api/UC/top-rated
// LISTAR TODOS LOS PRODUCTOS POR CATEGORIA -> (GET) http://localhost:3000/api/products/categories/hombre
// FILTRAR POR LO QUE ESCRIBA EL USUARIO (GET) -> http://localhost:3000/api/products/search?search=camiseta
// VER DETALLES DE UN PRODUCTO ESPECIFICO -> (GET) http://localhost:3000/api/UC/1 
// FINALIZAR COMPRA USUARIO -> (POST) http://localhost:3000/api/UC/purchase
// BODY FINALIZAR COMPRA USUARIO:
// {
//     "id_usuario": 1,
//     "productos": [
//         {
//             "producto_id": 1,
//             "cantidad": 2,
//             "precio_unitario": 15.00
//         },
//         {
//             "producto_id": 2,
//             "cantidad": 1,
//             "precio_unitario": 25.00
//         }
//     ]
// }
// VER HISTORIAL DE UN USUARIO ESPECIFICO -> (GET) http://localhost:3000/api/UC/history/1
//------------------------------------------------------------------------------------------------

// LISTAR TODOS LOS PRODUCTOS -> (GET) http://localhost:3000/api/products (SIN BODY)




// BODY PUNTUAR UN PRODUCTO:
// {
//     "producto_id": 33,
//     "usuario_id": 9,
//     "puntuacion": 5,
//     "comentario": "Excelente calidad y muy cómodas para trabajar."
// }

// LISTAR LOS PRODUCTOS DE UN VENDEDOR -> (GET) http://localhost:3000/api/products/seller/{id} (cambiar {id} por la id del vendedor)


// VER DETALLES DE UN PRODUCTO ESPECIFICO -> (GET) http://localhost:3000/api/products/{id} (cambiar {id} por el id del producto, por ejemplo la id 33)

// AÑADIR UN PRUDCTO AL CARRITO -> (POST) http://localhost:3000/api/users/cart
// BODY AGREGAR PRODUCTO AL CARRITO:
// {
//     "usuario_id": 7,
//     "producto_id": 33,
//     "cantidad": 2
// }


  