package com.example.r_zaragoza.UCFiltradoCateg.model;

import android.util.Log;

import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;
import com.example.r_zaragoza.utils.ApiService;
import com.example.r_zaragoza.utils.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UCFiltradoCategModel {
    private ApiService apiService;

    public UCFiltradoCategModel() {
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }

    public void obtenerProductosPorCategoria(String categoriaNombre, final ProductoCallback callback) {
        Call<List<UVListarProdModel>> call = apiService.obtenerProductosPorCategoria(categoriaNombre);
        call.enqueue(new Callback<List<UVListarProdModel>>() {
            @Override
            public void onResponse(Call<List<UVListarProdModel>> call, Response<List<UVListarProdModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("FiltrarCategoriaModel", "Productos obtenidos: " + response.body().size());
                    callback.onSuccess(response.body());
                } else {
                    Log.e("FiltrarCategoriaModel", "Error en la respuesta: Código " + response.code());
                    callback.onFailure("Error al obtener los productos: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<UVListarProdModel>> call, Throwable t) {
                Log.e("FiltrarCategoriaModel", "Error de conexión: " + t.getMessage());
                callback.onFailure("Fallo en la conexión: " + t.getMessage());
            }
        });
    }


    public interface ProductoCallback {
        void onSuccess(List<UVListarProdModel> productos);
        void onFailure(String message);
    }
}

