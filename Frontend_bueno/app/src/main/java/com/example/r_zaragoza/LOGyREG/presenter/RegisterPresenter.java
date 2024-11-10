package com.example.r_zaragoza.LOGyREG.presenter;

import com.example.r_zaragoza.LOGyREG.contracts.RegisterContract;
import com.example.r_zaragoza.LOGyREG.model.User;
import com.example.r_zaragoza.utils.ApiService;
import com.example.r_zaragoza.utils.RetrofitClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View view;
    private ApiService apiService;

    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }

    @Override
    public void onRegisterClicked() {
        String username = view.getUsername();
        String email = view.getEmail();
        String password = view.getPassword();
        String rol = view.getRol();

        User user = new User(username, email, password, rol);

        // Validar campos si es necesario

        // Enviar petición al backend
        Call<ResponseBody> call = apiService.register(user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    view.showRegisterSuccess("Registro exitoso");
                } else {
                    view.showRegisterError("Error en el registro");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showRegisterError("Fallo en la conexión: " + t.getMessage());
            }
        });
    }

}
