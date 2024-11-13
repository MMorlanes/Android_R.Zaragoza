package com.example.r_zaragoza.UCTop10Products.presenter;

import com.example.r_zaragoza.UCTop10Products.contracts.UCTop10ProductsContract;
import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;

import java.util.List;

public class UCTop10ProductPresenter implements UCTop10ProductsContract.Presenter, UCTop10ProductsContract.Model.OnFinishedListener {

    private UCTop10ProductsContract.View view;
    private UCTop10ProductsContract.Model model;

    public UCTop10ProductPresenter(UCTop10ProductsContract.View view, UCTop10ProductsContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void requestTop10Products() {
        if (view != null) {
            view.showLoading();
            model.fetchTop10Products(this);
        }
    }

    @Override
    public void onFinished(List<UVListarProdModel> productos) {
        if (view != null) {
            view.hideLoading();
            view.displayProducts(productos);
        }
    }

    @Override
    public void onFailure(String errorMessage) {
        if (view != null) {
            view.hideLoading();
            view.showError(errorMessage);
        }
    }
}
