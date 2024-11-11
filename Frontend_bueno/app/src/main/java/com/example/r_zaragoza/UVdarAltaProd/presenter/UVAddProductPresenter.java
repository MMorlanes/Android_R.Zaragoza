package com.example.r_zaragoza.UVdarAltaProd.presenter;

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
        String name = view.getProductName();
        String description = view.getProductDescription();
        String image = view.getProductImage();
        String category = view.getProductCategory();
        double price;

        try {
            price = Double.parseDouble(view.getProductPrice());
        } catch (NumberFormatException e) {
            view.showProductAddedError("Precio no válido.");
            return;
        }

        if (name.isEmpty() || description.isEmpty() || category.isEmpty() || price <= 0) {
            view.showProductAddedError("Todos los campos son obligatorios.");
            return;
        }

        // Suponemos que el idUsuario se obtiene de alguna parte, por ejemplo, del perfil del usuario logueado
        int idUsuario = 1; // Esto puede ser dinámico dependiendo de tu implementación

        Product product = new Product(idUsuario, name, description, image, price, category);

        // Hacer la petición al servidor para agregar el producto
        Call<ResponseBody> call = apiService.addProduct(product);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    view.showProductAddedSuccess("Producto agregado exitosamente.");
                } else {
                    view.showProductAddedError("Error al agregar el producto.");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showProductAddedError("Error de conexión: " + t.getMessage());
            }
        });
    }
}
