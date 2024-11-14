package com.example.r_zaragoza.UCPuntuarProd.contracts;

public interface UCPuntuarProdContract {
    interface View {
        void showLoading();
        void hideLoading();
        void showSuccessMessage(String message);
        void showErrorMessage(String message);
    }

    interface Presenter {
        void submitRating(int productId, int userId, int rating, String comment);
    }

    interface Model {
        void rateProduct(int productId, int userId, int rating, String comment, OnFinishedListener listener);

        interface OnFinishedListener {
            void onSuccess(String message);
            void onError(String message);
        }
    }
}
