package com.example.r_zaragoza.UCFinalizarCompra.contracts;

public interface FinalizarCompraContract {
    interface View {
        void showErrorMessage(String message);
        void showSuccessMessage(String message);
    }

    interface Presenter {
        void confirmPurchase(int userId, int productId, double precioUnitario); // Añadir precioUnitario

        void confirmPurchase(int userId, int productId, String precioUnitarioStr);
    }

    interface Model {
        void confirmPurchase(int userId, int productId, double precioUnitario, OnPurchaseConfirmedListener listener); // Añadir precioUnitario

        interface OnPurchaseConfirmedListener {
            void onPurchaseSuccess();
            void onError(String error);
        }
    }
}
