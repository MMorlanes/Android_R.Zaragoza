package com.example.r_zaragoza.UCFiltradoCateg.contracts;

import com.example.r_zaragoza.UCFiltradoCateg.model.UCFiltradoCategModel;
import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;

import java.util.List;

public interface UCFiltradoCategContract {
    interface View {
        void showCategorias(List<UVListarProdModel> productos); // Cambiado a Producto
        void showError(String message);
    }

    interface Presenter {
        void loadCategorias();
    }
}
