package com.example.r_zaragoza.UCBusquedaTexto.model;

import com.example.r_zaragoza.UCBusquedaTexto.contracts.BusquedaTextoContract;
import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;
import com.example.r_zaragoza.utils.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusquedaTextoModel implements BusquedaTextoContract.Model {

    private ApiService apiService;

    public BusquedaTextoModel(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void searchProducts(String query, OnFinishedListener listener) {
        apiService.searchProducts(query).enqueue(new Callback<List<UVListarProdModel>>() {
            @Override
            public void onResponse(Call<List<UVListarProdModel>> call, Response<List<UVListarProdModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onFinished(response.body());
                } else {
                    listener.onFailure("No se encontraron productos.");
                }
            }

            @Override
            public void onFailure(Call<List<UVListarProdModel>> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }
}