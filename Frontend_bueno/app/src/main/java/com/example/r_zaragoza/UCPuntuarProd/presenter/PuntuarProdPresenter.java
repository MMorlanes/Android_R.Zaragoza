package com.example.r_zaragoza.UCPuntuarProd.presenter;

import android.util.Log;
import com.example.r_zaragoza.UCPuntuarProd.contracts.UCPuntuarProdContract;

public class PuntuarProdPresenter implements UCPuntuarProdContract.Presenter, UCPuntuarProdContract.Model.OnFinishedListener {

    private UCPuntuarProdContract.View view;
    private UCPuntuarProdContract.Model model;
    private static final String TAG = "PuntuarProductoPresenter";

    public PuntuarProdPresenter(UCPuntuarProdContract.View view, UCPuntuarProdContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void submitRating(int productId, int userId, int rating, String comment) {
        if (view != null) {
            view.showLoading();
        }

        Log.d(TAG, "Enviando puntuación: Producto ID: " + productId + ", Usuario ID: " + userId + ", Puntuación: " + rating + ", Comentario: " + comment);

        model.rateProduct(productId, userId, rating, comment, this);
    }

    @Override
    public void onSuccess(String message) {
        if (view != null) {
            view.hideLoading();
            view.showSuccessMessage(message);
        }
    }

    @Override
    public void onError(String message) {
        if (view != null) {
            view.hideLoading();
            view.showErrorMessage(message);
        }
    }
}
