package com.example.r_zaragoza.utils;

import com.example.r_zaragoza.LOGyREG.model.User;
import com.example.r_zaragoza.UVdarAltaProd.model.Product;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("api/LOGyREG/login")
    Call<ResponseBody> login(@Body User user);

    @POST("api/LOGyREG/register")
    Call<ResponseBody> register(@Body User user);

    @POST("api/UVdarAltaProd/addProducts")
    Call<ResponseBody> addProduct(@Body Product product);

}
