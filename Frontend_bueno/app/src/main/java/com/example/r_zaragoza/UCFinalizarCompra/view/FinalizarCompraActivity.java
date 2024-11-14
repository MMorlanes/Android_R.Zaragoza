package com.example.r_zaragoza.UCFinalizarCompra.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.r_zaragoza.R;
import com.example.r_zaragoza.UCFinalizarCompra.contracts.FinalizarCompraContract;
import com.example.r_zaragoza.UCFinalizarCompra.model.FinalizarCompraModel;
import com.example.r_zaragoza.UCFinalizarCompra.presenter.FinalizarCompraPresenter;
import com.example.r_zaragoza.utils.RetrofitClient;
import com.example.r_zaragoza.utils.ApiService;

public class FinalizarCompraActivity extends AppCompatActivity implements FinalizarCompraContract.View {
    private FinalizarCompraPresenter presenter;
    private Button btnConfirmPurchase;
    private String unitPrice;  // Cambiado a String
    private TextView tvUnitPrice;
    private static final String TAG = "FinalizarCompraActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_compra);

        tvUnitPrice = findViewById(R.id.tvUnitPrice);
        btnConfirmPurchase = findViewById(R.id.btnConfirmPurchase);

        int productId = getIntent().getIntExtra("productId", -1);
        int userId = getIntent().getIntExtra("userId", -1);
        unitPrice = getIntent().getStringExtra("precio_unitario"); // Obtener precio_unitario como String

        Log.d(TAG, "ID del producto: " + productId);
        Log.d(TAG, "ID del usuario: " + userId);
        Log.d(TAG, "Precio unitario recibido en Activity: " + unitPrice);

        // Mostrar el precio unitario
        tvUnitPrice.setText("Precio por unidad: €" + unitPrice);

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        presenter = new FinalizarCompraPresenter(this, new FinalizarCompraModel(apiService), this);

        btnConfirmPurchase.setOnClickListener(v -> {
            if (unitPrice != null && !unitPrice.isEmpty()) {
                try {
                    double precioUnitario = Double.parseDouble(unitPrice);
                    Log.d(TAG, "Botón de confirmación de compra presionado");

                    // Verificar IDs válidos
                    if (productId != -1 && userId != -1) {
                        Log.d(TAG, "Enviando la id de usuario: " + userId);
                        Log.d(TAG, "Enviando la id de producto: " + productId);
                        Log.d(TAG, "Enviando el precio: " + precioUnitario);

                        presenter.confirmPurchase(userId, productId, precioUnitario);
                    } else {
                        showErrorMessage("ID de usuario o producto no válido.");
                    }
                } catch (NumberFormatException e) {
                    Log.e(TAG, "Error al convertir el precio unitario: " + unitPrice, e);
                    showErrorMessage("Precio unitario inválido.");
                }
            } else {
                showErrorMessage("Precio unitario no disponible.");
            }
        });


    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }
}
