package com.example.r_zaragoza.UCBusquedaTexto.contracts;

import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;

import java.util.List;

public interface BusquedaTextoContract {
    interface View {
        void showLoading();
        void hideLoading();
        void showProducts(List<UVListarProdModel> productos);
        void showError(String error);
    }

    interface Presenter {
        void searchProducts(String query);
    }

    interface Model {
        void searchProducts(String query, OnFinishedListener listener);

        interface OnFinishedListener {
            void onFinished(List<UVListarProdModel> productos);
            void onFailure(String error);
        }
    }
}
