package com.example.r_zaragoza.UCDetalleProd.model;

import com.example.r_zaragoza.UCDetalleProd.contracts.DetalleProdContract;
import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;
import com.example.r_zaragoza.utils.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleProdModel implements DetalleProdContract.Model {

    private ApiService apiService;

    public DetalleProdModel(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void fetchProductDetails(int productId, OnFinishedListener listener) {
        apiService.getProductDetails(productId).enqueue(new Callback<UVListarProdModel>() {
            @Override
            public void onResponse(Call<UVListarProdModel> call, Response<UVListarProdModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onFinished(response.body());
                } else {
                    listener.onFailure("No se pudo obtener los detalles del producto.");
                }
            }

            @Override
            public void onFailure(Call<UVListarProdModel> call, Throwable t) {
                listener.onFailure("Error de conexión: " + t.getMessage());
            }
        });
    }
}
