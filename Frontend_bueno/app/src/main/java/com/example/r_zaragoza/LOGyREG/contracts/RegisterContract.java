package com.example.r_zaragoza.LOGyREG.contracts;

public interface RegisterContract {
    interface View {
        String getUsername();
        String getEmail();
        String getPassword();
        String getRol();
        void showRegisterSuccess(String message);
        void showRegisterError(String message);
    }

    interface Presenter {
        void onRegisterClicked();
    }
}
