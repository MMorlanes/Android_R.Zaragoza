package com.example.r_zaragoza.UCBusquedaTexto.presenter;

import com.example.r_zaragoza.UCBusquedaTexto.contracts.BusquedaTextoContract;
import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;

import java.util.List;

public class BusquedaTextoPresenter implements BusquedaTextoContract.Presenter, BusquedaTextoContract.Model.OnFinishedListener {

    private BusquedaTextoContract.View view;
    private BusquedaTextoContract.Model model;

    public BusquedaTextoPresenter(BusquedaTextoContract.View view, BusquedaTextoContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void searchProducts(String query) {
        view.showLoading();
        model.searchProducts(query, this);
    }

    @Override
    public void onFinished(List<UVListarProdModel> productos) {
        view.hideLoading();
        if (productos.isEmpty()) {
            view.showError("No se encontraron productos.");
        } else {
            view.showProducts(productos);
        }
    }

    @Override
    public void onFailure(String error) {
        view.hideLoading();
        view.showError(error);
    }
}
