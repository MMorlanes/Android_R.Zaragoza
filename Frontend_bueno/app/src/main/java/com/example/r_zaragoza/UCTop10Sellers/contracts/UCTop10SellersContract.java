package com.example.r_zaragoza.UCTop10Sellers.contracts;

import com.example.r_zaragoza.UCTop10Sellers.model.Seller;

import java.util.List;

public interface UCTop10SellersContract {
    interface View {
        void showTopSellers(List<Seller> sellers);
        void showError(String message);
    }

    interface Presenter {
        void loadTopSellers();
    }
}
