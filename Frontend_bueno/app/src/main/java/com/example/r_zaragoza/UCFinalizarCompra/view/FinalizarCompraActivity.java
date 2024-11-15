package com.example.r_zaragoza.UCFinalizarCompra.view;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.r_zaragoza.LOGyREG.view.LoginActivity;
import com.example.r_zaragoza.R;
import com.example.r_zaragoza.UCFinalizarCompra.contracts.FinalizarCompraContract;
import com.example.r_zaragoza.UCFinalizarCompra.model.FinalizarCompraModel;
import com.example.r_zaragoza.UCFinalizarCompra.presenter.FinalizarCompraPresenter;
import com.example.r_zaragoza.UCHistoricoCompras.view.UCHistoricoComprasActivity;
import com.example.r_zaragoza.UVdarAltaProd.model.Product;
import com.example.r_zaragoza.utils.Email;
import com.example.r_zaragoza.utils.EmailSender;
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
        Email email = new Email("Encabezado", "Cuerpo", "a27737@svalero.com");
        new EmailSender(email).execute();
        createNotification(this, "A", "aaa");
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }


    void createNotification(Context context, String subject, String body) {
        Log.e("Notification", "Attempting to create notification");

        String productNames = "";

//        for (Product product: productList) {
//            productNames += "- " + product.getName() + "\n";
//        }

        // Create an Intent for the action when the notification is clicked
        Intent intent = new Intent(context, LoginActivity.class);  // Replace YourActivity with the activity to launch
    //        intent.putExtra("productNames", productNames);  // Optional: pass data to the activity

        // Create a PendingIntent, which will be triggered when the notification is clicked
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default_channel")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(subject)
                .setContentText(body)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(body))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelId = "default_channel";
                CharSequence channelName = "Canal principal";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);

                try {
                    Log.e("NotificationSuccess", "");
                    notificationManager.createNotificationChannel(channel);
                } catch (Exception e) {
                    Log.e("NotificationError", "Failed to create notification channel", e);
                }
            }

            notificationManager.notify(1, builder.build());
        } else {
            Log.e("NotificationError", "Notification Manager is null");
        }
    }
}
