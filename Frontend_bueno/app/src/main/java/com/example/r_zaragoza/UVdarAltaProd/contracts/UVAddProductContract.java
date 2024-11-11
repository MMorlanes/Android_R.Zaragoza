package com.example.r_zaragoza.UVdarAltaProd.contracts;

public interface UVAddProductContract {
    interface View {
        String getProductName();
        String getProductDescription();
        String getProductImage();
        String getProductPrice();
        String getProductCategory();
        void showProductAddedSuccess(String message);
        void showProductAddedError(String message);
    }

    interface Presenter {
        void onAddProductClicked();
    }
}
