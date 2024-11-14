package com.example.r_zaragoza.UCFinalizarCompra.model;

import android.util.Log;
import com.example.r_zaragoza.UCFinalizarCompra.contracts.FinalizarCompraContract;
import com.example.r_zaragoza.utils.ApiService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalizarCompraModel implements FinalizarCompraContract.Model {
    private ApiService apiService;
    private static final String TAG = "FinalizarCompraModel";

    public FinalizarCompraModel(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void confirmPurchase(int userId, int productId, double precioUnitario, OnPurchaseConfirmedListener listener) {
        // Crear la solicitud de compra incluyendo precio_unitario
        ConfirmPurchaseRequest request = new ConfirmPurchaseRequest(userId, productId, precioUnitario);

        Log.d(TAG, "Enviando solicitud de compra a la API");
        Log.d(TAG, "Enviando la id de usuario: " + userId);
        Log.d(TAG, "Enviando la id de producto: " + productId);
        Log.d(TAG, "Enviando el precio: " + precioUnitario);

        // Llamada a la API para confirmar la compra
        Call<ResponseBody> call = apiService.makePurchase(request);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Compra confirmada exitosamente en el servidor.");
                    listener.onPurchaseSuccess();
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e(TAG, "Error en la respuesta de la API. Código de respuesta: " + response.code());
                        Log.e(TAG, "Mensaje de error en la respuesta de la API: " + errorBody);
                        listener.onError("Error al confirmar la compra: " + errorBody);
                    } catch (Exception e) {
                        Log.e(TAG, "No se pudo leer el cuerpo de error.", e);
                        listener.onError("Error al confirmar la compra.");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error de conexión al servidor: " + t.getMessage());
                listener.onError("Error de conexión: " + t.getMessage());
            }
        });
    }


}
