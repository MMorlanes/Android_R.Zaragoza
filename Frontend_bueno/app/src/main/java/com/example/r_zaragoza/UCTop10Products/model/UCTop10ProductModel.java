package com.example.r_zaragoza.UCTop10Products.model;

import com.example.r_zaragoza.UCTop10Products.contracts.UCTop10ProductsContract;
import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;
import com.example.r_zaragoza.utils.ApiService;
import com.example.r_zaragoza.utils.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UCTop10ProductModel implements UCTop10ProductsContract.Model {

    @Override
    public void fetchTop10Products(OnFinishedListener listener) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        Call<List<UVListarProdModel>> call = apiService.getTopRatedProducts();
        call.enqueue(new Callback<List<UVListarProdModel>>() {
            @Override
            public void onResponse(Call<List<UVListarProdModel>> call, Response<List<UVListarProdModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onFinished(response.body());
                } else {
                    listener.onFailure("No se pudieron obtener los productos");
                }
            }

            @Override
            public void onFailure(Call<List<UVListarProdModel>> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }
}
