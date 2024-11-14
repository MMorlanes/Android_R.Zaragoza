package com.example.r_zaragoza.UCFinalizarCompra.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.example.r_zaragoza.UCFinalizarCompra.contracts.FinalizarCompraContract;

public class FinalizarCompraPresenter implements FinalizarCompraContract.Presenter {
    private FinalizarCompraContract.View view;
    private FinalizarCompraContract.Model model;
    private Context context;
    private static final String TAG = "FinalizarCompraPresenter";

    public FinalizarCompraPresenter(FinalizarCompraContract.View view, FinalizarCompraContract.Model model, Context context) {
        this.view = view;
        this.model = model;
        this.context = context;
    }

    @Override
    public void confirmPurchase(int userId, int productId, double precioUnitario) {
        Log.d(TAG, "Confirmando compra en Presenter");
        Log.d(TAG, "Enviando la id de usuario: " + userId);
        Log.d(TAG, "Enviando la id de producto: " + productId);
        Log.d(TAG, "Enviando el precio: " + precioUnitario);

        Toast.makeText(context, "Procesando compra...", Toast.LENGTH_SHORT).show();

        model.confirmPurchase(userId, productId, precioUnitario, new FinalizarCompraContract.Model.OnPurchaseConfirmedListener() {
            @Override
            public void onPurchaseSuccess() {
                view.showSuccessMessage("Compra confirmada exitosamente.");
            }

            @Override
            public void onError(String error) {
                view.showErrorMessage(error);
            }
        });
    }



    @Override
    public void confirmPurchase(int userId, int productId, String precioUnitarioStr) {
        try {
            double precioUnitario = Double.parseDouble(precioUnitarioStr);
            confirmPurchase(userId, productId, precioUnitario);
        } catch (NumberFormatException e) {
            Log.e(TAG, "Error al convertir precio unitario: " + precioUnitarioStr, e);
            view.showErrorMessage("Precio unitario inválido.");
        }
    }
}
