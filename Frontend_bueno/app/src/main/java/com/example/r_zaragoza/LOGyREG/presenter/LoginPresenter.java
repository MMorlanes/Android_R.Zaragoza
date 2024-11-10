package com.example.r_zaragoza.LOGyREG.presenter;

import com.example.r_zaragoza.LOGyREG.contracts.LoginContract;
import com.example.r_zaragoza.LOGyREG.model.User;
import com.example.r_zaragoza.utils.ApiService;
import com.example.r_zaragoza.utils.RetrofitClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private ApiService apiService;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }

    @Override
    public void onLoginClicked() {
        String email = view.getEmail();
        String password = view.getPassword();

        User user = new User(email, password);

        // Validar campos si es necesario

        // Enviar petición al backend
        Call<ResponseBody> call = apiService.login(user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    view.showLoginSuccess("Login exitoso");
                } else {
                    view.showLoginError("Error en el login");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showLoginError("Fallo en la conexión: " + t.getMessage());
            }
        });
    }
}
