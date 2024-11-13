package com.example.r_zaragoza.UVListarProd.presenter;

import android.util.Log;

import com.example.r_zaragoza.UVListarProd.contracts.UVListarProdContract;
import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;
import com.example.r_zaragoza.utils.ApiService;
import com.example.r_zaragoza.utils.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UVListarProdPresenter implements UVListarProdContract.Presenter {

    private UVListarProdContract.View view;

    public UVListarProdPresenter(UVListarProdContract.View view) {
        this.view = view;
    }

    @Override
    public void loadProductos() {
        int vendedorId = view.getVendedorId();
        Log.d("ListarProductosPresenter", "Vendedor ID: " + vendedorId);

        // Crear la instancia de ApiService
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        // Llamada para obtener directamente una lista de productos
        Call<List<UVListarProdModel>> call = apiService.getProductosVendedor(vendedorId);

        // Hacer la solicitud
        call.enqueue(new Callback<List<UVListarProdModel>>() {
            @Override
            public void onResponse(Call<List<UVListarProdModel>> call, Response<List<UVListarProdModel>> response) {
                // Verificar si la respuesta es exitosa y si hay productos
                if (response.isSuccessful() && response.body() != null) {
                    List<UVListarProdModel> productos = response.body(); // Lista de productos directamente
                    if (productos != null && !productos.isEmpty()) {
                        Log.d("ListarProductosPresenter", "Productos obtenidos exitosamente: " + productos.size());
                        // Mostrar los productos en la vista
                        view.showProductos(productos);
                    } else {
                        Log.e("ListarProductosPresenter", "La lista de productos está vacía.");
                        view.showError("No se encontraron productos.");
                    }
                } else {
                    Log.e("ListarProductosPresenter", "Error en la respuesta: " + response.code() + " - " + response.message());
                    view.showError("Error al cargar los productos.");
                }
            }

            @Override
            public void onFailure(Call<List<UVListarProdModel>> call, Throwable t) {
                // Manejar el fallo de la solicitud
                Log.e("ListarProductosPresenter", "Error en la solicitud: " + t.getMessage());
                view.showError("No se pudo conectar al servidor.");
            }
        });
    }
}
