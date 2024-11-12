package com.example.r_zaragoza.UVdarAltaProd.presenter;

import android.util.Log;

import com.example.r_zaragoza.UVdarAltaProd.contracts.UVAddProductContract;
import com.example.r_zaragoza.UVdarAltaProd.model.Product;
import com.example.r_zaragoza.utils.ApiService;
import com.example.r_zaragoza.utils.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UVAddProductPresenter implements UVAddProductContract.Presenter {
    private UVAddProductContract.View view;
    private ApiService apiService;

    public UVAddProductPresenter(UVAddProductContract.View view) {
        this.view = view;
        this.apiService = RetrofitClient.getClient().create(ApiService.class);
    }

    @Override
    public void onAddProductClicked() {
        // Obtener datos de la vista
        int userId = view.getUserId();
        String name = view.getProductName();
        String description = view.getProductDescription();
        String image = view.getProductImage();
        String category = view.getProductCategory();
        double price;

        // Validar precio
        try {
            price = Double.parseDouble(view.getProductPrice());
        } catch (NumberFormatException e) {
            view.showProductAddedError("Precio no válido.");
            return;
        }

        // Validar campos obligatorios
        if (name.isEmpty() || description.isEmpty() || category.isEmpty() || price <= 0) {
            view.showProductAddedError("Todos los campos son obligatorios.");
            return;
        }

        // Crear objeto producto
        Product product = new Product(userId, name, description, image, price, category);

        // Log para verificar los datos enviados
        Log.d("AgregarProducto", "Producto a enviar: " + product);

        // Enviar datos al servidor
        Call<ResponseBody> call = apiService.addProduct(product);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    view.showProductAddedSuccess("Producto agregado exitosamente.");
                } else {
                    Log.e("AgregarProducto", "Error en respuesta: " + response.code());
                    view.showProductAddedError("Error al agregar el producto.");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("AgregarProducto", "Error en la conexión: " + t.getMessage());
                view.showProductAddedError("Error de conexión: " + t.getMessage());
            }
        });
    }
}
