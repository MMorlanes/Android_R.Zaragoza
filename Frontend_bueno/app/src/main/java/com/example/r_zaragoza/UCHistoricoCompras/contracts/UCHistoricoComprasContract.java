package com.example.r_zaragoza.UCHistoricoCompras.contracts;

import com.example.r_zaragoza.UCHistoricoCompras.model.PedidoProd;
import com.example.r_zaragoza.UCHistoricoCompras.model.UCHistoricoComprasModel;
import java.util.List;

public interface UCHistoricoComprasContract {
    interface View {
        void showLoading();
        void hideLoading();
        void showHistory(List<PedidoProd> historial);
        void showError(String error);
    }

    interface Presenter {
        void loadUserHistory(int userId);
    }

    interface Model {
        void fetchUserHistory(int userId, OnFinishedListener listener);

        interface OnFinishedListener {
            void onFinished(List<PedidoProd> historial);
            void onFailure(String error);
        }
    }
}

