package com.example.r_zaragoza.utils;

import com.example.r_zaragoza.LOGyREG.model.User;
import com.example.r_zaragoza.UCTop10Sellers.model.Seller;
import com.example.r_zaragoza.UVListarProd.model.Producto;
import com.example.r_zaragoza.UVdarAltaProd.model.Product;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("api/LOGyREG/login")
    Call<ResponseBody> login(@Body User user);

    @POST("api/LOGyREG/register")
    Call<ResponseBody> register(@Body User user);

    @POST("api/UV/addProducts")
    Call<ResponseBody> addProduct(@Body Product product);

    // Ruta para obtener los productos de un vendedor específico
    @GET("/api/UV/seller/{id_usuario}")
    Call<List<Producto>> getProductosVendedor(@Path("id_usuario") int idUsuario);

    @GET("api/UCTop10SellersActivity/top_sellers")
    Call<ResponseBody> top_sellers(@Body Seller seller);
}
