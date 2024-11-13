package com.example.r_zaragoza.UVListarProd.contracts;
import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;

import java.util.List;


public interface UVListarProdContract {
    interface View {
        int getVendedorId();
        void showProductos(List<UVListarProdModel> productos);
        void showError(String message);
    }

    interface Presenter {
        void loadProductos();
    }

}
