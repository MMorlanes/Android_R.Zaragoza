package com.example.r_zaragoza.LOGyREG.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.r_zaragoza.LOGyREG.contracts.LoginContract;
import com.example.r_zaragoza.LOGyREG.model.User;
import com.example.r_zaragoza.utils.ApiService;
import com.example.r_zaragoza.utils.RetrofitClient;

import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG = "LoginPresenter";
    private LoginContract.View view;
    private ApiService apiService;
    private Context context;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.context = (Context) view;  // Asegúrate de que 'view' es una instancia de 'Context'
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }

    @Override
    public void onLoginClicked() {
        String email = view.getEmail();
        String password = view.getPassword();

        User user = new User(email, password);

        Call<ResponseBody> call = apiService.login(user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        // Verificar que el cuerpo de la respuesta no sea nulo
                        if (response.body() != null) {
                            // Leer la respuesta en formato String
                            String responseBodyString = response.body().string();

                            // Log para mostrar la respuesta completa
                            Log.d(TAG, "Respuesta completa del servidor: " + responseBodyString);

                            // Convertir el String en un JSONObject
                            JSONObject jsonResponse = new JSONObject(responseBodyString);

                            // Verificar si el JSON tiene la clave 'user'
                            if (jsonResponse.has("user")) {
                                JSONObject userObject = jsonResponse.getJSONObject("user");

                                // Obtener el ID y el rol del usuario desde el objeto 'user'
                                int userId = userObject.optInt("id_usuario", -1);  // Cambiar "id" por "id_usuario"
                                String role = userObject.optString("rol", "");

                                // Log para verificar el ID y rol obtenidos
                                Log.d(TAG, "ID del usuario: " + userId);
                                Log.d(TAG, "Rol del usuario: " + role);

                                // Almacenar el ID del usuario en SharedPreferences
                                if (userId != -1) {
                                    SharedPreferences sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putInt("userId", userId);
                                    editor.apply();

                                    Log.d(TAG, "ID del usuario almacenado: " + userId);
                                }

                                // Verificar el rol y redirigir a la vista correspondiente
                                if ("vendedor".equalsIgnoreCase(role.trim())) {
                                    view.showLoginSuccess("Login exitoso", "vendedor");
                                } else if ("cliente".equalsIgnoreCase(role.trim())) {
                                    view.showLoginSuccess("Login exitoso", "cliente");
                                } else {
                                    Log.e(TAG, "Rol de usuario desconocido");
                                    view.showLoginError("Rol de usuario desconocido.");
                                }
                            } else {
                                Log.e(TAG, "Clave 'user' no encontrada en la respuesta");
                                view.showLoginError("Usuario no encontrado en la respuesta del servidor");
                            }
                        } else {
                            Log.e(TAG, "El cuerpo de la respuesta está vacío");
                            view.showLoginError("Cuerpo de la respuesta vacío");
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Excepción al procesar la respuesta del servidor", e);
                        view.showLoginError("Error al procesar la respuesta del servidor");
                    }
                } else {
                    Log.e(TAG, "Error en el login. Código de respuesta: " + response.code());
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
