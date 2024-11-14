package com.example.r_zaragoza.UCHistoricoCompras.presenter;

import com.example.r_zaragoza.UCHistoricoCompras.contracts.UCHistoricoComprasContract;
import com.example.r_zaragoza.UCHistoricoCompras.model.PedidoProd;
import java.util.List;

public class UCHistoricoComprasPresenter implements UCHistoricoComprasContract.Presenter, UCHistoricoComprasContract.Model.OnFinishedListener {

    private UCHistoricoComprasContract.View view;
    private UCHistoricoComprasContract.Model model;

    public UCHistoricoComprasPresenter(UCHistoricoComprasContract.View view, UCHistoricoComprasContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void loadUserHistory(int userId) {
        view.showLoading();
        model.fetchUserHistory(userId, this);
    }

    @Override
    public void onFinished(List<PedidoProd> historial) {
        view.hideLoading();
        view.showHistory(historial);
    }

    @Override
    public void onFailure(String error) {
        view.hideLoading();
        view.showError(error);
    }
}
