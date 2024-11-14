package com.example.r_zaragoza.UCDetalleProd.presenter;

import com.example.r_zaragoza.UCDetalleProd.contracts.DetalleProdContract;
import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;

public class DetalleProdPresenter implements DetalleProdContract.Presenter, DetalleProdContract.Model.OnFinishedListener {

    private DetalleProdContract.View view;
    private DetalleProdContract.Model model;
    private boolean isClient;

    public DetalleProdPresenter(DetalleProdContract.View view, DetalleProdContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void loadProductDetails(int productId, boolean isClient) {
        this.isClient = isClient;
        model.fetchProductDetails(productId, this);
    }

    @Override
    public void onFinished(UVListarProdModel producto) {
        view.showProductDetails(producto);
        if (isClient) {
            view.showBuyAndRateOptions();
        }
    }

    @Override
    public void onFailure(String message) {
        view.showError(message);
    }
}
