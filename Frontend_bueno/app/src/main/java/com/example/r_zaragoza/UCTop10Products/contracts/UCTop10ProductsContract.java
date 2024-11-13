package com.example.r_zaragoza.UCTop10Products.contracts;

import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;

import java.util.List;

public interface UCTop10ProductsContract {

    interface View {
        void showLoading();
        void hideLoading();
        void displayProducts(List<UVListarProdModel> productos);
        void showError(String message);
    }

    interface Presenter {
        void requestTop10Products();
    }

    interface Model {
        interface OnFinishedListener {
            void onFinished(List<UVListarProdModel> productos);
            void onFailure(String errorMessage);
        }

        void fetchTop10Products(OnFinishedListener listener);
    }
}
