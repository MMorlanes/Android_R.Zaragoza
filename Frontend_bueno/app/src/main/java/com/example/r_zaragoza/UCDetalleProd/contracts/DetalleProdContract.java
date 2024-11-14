package com.example.r_zaragoza.UCDetalleProd.contracts;

import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;

public interface DetalleProdContract {

    interface View {
        void showProductDetails(UVListarProdModel producto);
        void showError(String message);
        void showBuyAndRateOptions(); // Mostrar los botones del cliente
    }

    interface Presenter {
        void loadProductDetails(int productId, boolean isClient);
    }

    interface Model {
        void fetchProductDetails(int productId, OnFinishedListener listener);

        interface OnFinishedListener {
            void onFinished(UVListarProdModel producto);
            void onFailure(String message);
        }
    }
}