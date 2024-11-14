package com.example.r_zaragoza.UCPuntuarProd.model;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.r_zaragoza.UCPuntuarProd.contracts.UCPuntuarProdContract;
import com.example.r_zaragoza.utils.ApiService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.io.IOException;

public class PuntuarProdModel implements UCPuntuarProdContract.Model {

    private ApiService apiService;
    private Context context;
    private static final String TAG = "PuntuarProductoModel";

    public PuntuarProdModel(ApiService apiService, Context context) {
        this.apiService = apiService;
        this.context = context;
    }

    @Override
    public void rateProduct(int producto_id, int usuario_id, int puntuacion, String comentario, OnFinishedListener listener) {
        RateProdRequest request = new RateProdRequest(producto_id, usuario_id, puntuacion, comentario);
        Call<Void> call = apiService.rateProduct(request);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess("Producto puntuado con éxito");
                } else {
                    String errorMessage = "Error al puntuar el producto";
                    ResponseBody errorBody = response.errorBody();

                    if (errorBody != null) {
                        try {
                            String errorResponse = errorBody.string();
                            Log.e(TAG, "Mensaje de error del servidor: " + errorResponse);

                            // Verificar si el mensaje contiene el texto específico que indica puntuación duplicada
                            if (errorResponse.contains("Este usuario ya ha puntuado dicho producto")) {
                                errorMessage = "Ya has puntuado este producto. No se permite puntuar de nuevo.";

                                // Mostrar Toast con el mensaje específico en el hilo principal
                                new Handler(Looper.getMainLooper()).post(() ->
                                        Toast.makeText(context, "Ya has puntuado este producto.", Toast.LENGTH_SHORT).show()
                                );

                                // Notificar al listener de este mensaje específico
                                listener.onError(errorMessage);
                                return;
                            }
                        } catch (IOException e) {
                            Log.e(TAG, "Error al leer el cuerpo de error", e);
                            listener.onError("Error al procesar la respuesta del servidor");
                        } finally {
                            errorBody.close();
                        }
                    }

                    // Llamar al listener con un mensaje de error general si no coincide
                    listener.onError(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onError("Error de conexión: " + t.getMessage());
            }
        });
    }
}
