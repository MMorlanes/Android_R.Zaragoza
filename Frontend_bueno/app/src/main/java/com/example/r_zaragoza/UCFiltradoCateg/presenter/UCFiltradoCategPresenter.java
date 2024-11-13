package com.example.r_zaragoza.UCFiltradoCateg.presenter;

import android.util.Log;
import com.example.r_zaragoza.UCFiltradoCateg.contracts.UCFiltradoCategContract;
import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;
import com.example.r_zaragoza.UCFiltradoCateg.model.UCFiltradoCategModel;
import java.util.List;

public class UCFiltradoCategPresenter implements UCFiltradoCategContract.Presenter {

    private UCFiltradoCategContract.View view;
    private UCFiltradoCategModel model;
    private String categoriaNombre;  // Usamos nombre de la categoría en vez de id

    public UCFiltradoCategPresenter(UCFiltradoCategContract.View view, String categoriaNombre) {
        this.view = view;
        this.model = new UCFiltradoCategModel();
        this.categoriaNombre = categoriaNombre;
    }

    @Override
    public void loadCategorias() {
        Log.d("FiltrarCategoriaPresenter", "Categoria: " + categoriaNombre);

        model.obtenerProductosPorCategoria(categoriaNombre, new UCFiltradoCategModel.ProductoCallback() {
            @Override
            public void onSuccess(List<UVListarProdModel> productos) {
                Log.d("FiltrarCategoriaPresenter", "Productos obtenidos: " + productos.size());
                view.showCategorias(productos);
            }

            @Override
            public void onFailure(String message) {
                view.showError(message);  // Muestra el mensaje de error en caso de fallo
            }
        });
    }
}
