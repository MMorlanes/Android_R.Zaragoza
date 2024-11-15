package com.example.r_zaragoza.LOGyREG.contracts;

public interface LoginContract {
    interface View {
        String getEmail();
        String getPassword();
        void showLoginSuccess(String message, String role);
        void showLoginError(String message);
    }

    interface Presenter {
        void onLoginClicked();
    }
}
