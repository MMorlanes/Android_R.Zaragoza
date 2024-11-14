package com.example.r_zaragoza.utils;

import com.example.r_zaragoza.LOGyREG.model.User;
import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;
import com.example.r_zaragoza.UVdarAltaProd.model.Product;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST("api/LOGyREG/login")
    Call<ResponseBody> login(@Body User user);

    @POST("api/LOGyREG/register")
    Call<ResponseBody> register(@Body User user);

    @POST("api/UV/addProducts")
    Call<ResponseBody> addProduct(@Body Product product);

    // Ruta para obtener los productos de un vendedor específico
    @GET("/api/UV/seller/{id_usuario}")
    Call<List<UVListarProdModel>> getProductosVendedor(@Path("id_usuario") int idUsuario);

    @GET("api/UC/top_sellers")
    Call<ResponseBody> top_sellers();

    @GET("api/UC/top-rated")
    Call<List<UVListarProdModel>> getTopRatedProducts();

    @GET("api/UC/products/categories/{nombreCategoria}")
    Call<List<UVListarProdModel>> obtenerProductosPorCategoria(@Path("nombreCategoria") String nombreCategoria);

    @GET("api/UC/{id_producto}")
    Call<UVListarProdModel> getProductoDetalle(@Path("id_producto") int idProducto);

    // Ruta para buscar productos por texto
    @GET("api/UC/search")
    Call<List<UVListarProdModel>> searchProducts(@Query("search") String searchQuery);

    //Ruta para los detalles de los productos
    @GET("api/UC/{id}")
    Call<UVListarProdModel> getProductDetails(@Path("id") int id);

}
