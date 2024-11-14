package com.example.r_zaragoza.UCDetalleProd.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.r_zaragoza.UCDetalleProd.contracts.DetalleProdContract;
import com.example.r_zaragoza.UCDetalleProd.model.DetalleProdModel;
import com.example.r_zaragoza.UCDetalleProd.presenter.DetalleProdPresenter;
import com.example.r_zaragoza.UCPuntuarProd.view.PuntuarProdActivity;
import com.example.r_zaragoza.R;
import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;
import com.example.r_zaragoza.utils.RetrofitClient;
import com.example.r_zaragoza.utils.ApiService;
import com.example.r_zaragoza.UCFinalizarCompra.view.FinalizarCompraActivity;

public class DetalleProdActivity extends AppCompatActivity implements DetalleProdContract.View {

    private static final String TAG = "DetalleProductoActivity";
    private TextView tvNombre, tvDescripcion, tvPrecio;
    private ImageView ivImagen;
    private Button btnPuntuar, btnComprar;
    private DetalleProdPresenter presenter;
    private boolean isClient;
    private int productId, userId;
    private String unitPrice;  // Cambiado a String para consistencia

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_producto);

        tvNombre = findViewById(R.id.tvNombre);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvPrecio = findViewById(R.id.tvPrecio);

        ivImagen = findViewById(R.id.ivProductoImagen);
        btnPuntuar = findViewById(R.id.btnPuntuar);
        btnComprar = findViewById(R.id.btnComprar);

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        presenter = new DetalleProdPresenter(this, new DetalleProdModel(apiService));

        productId = getIntent().getIntExtra("productId", -1);
        isClient = getIntent().getBooleanExtra("isClient", false);

        // Obtener el ID del usuario de SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", -1);

        Log.d(TAG, "onCreate: Product ID: " + productId + ", User ID: " + userId);

        presenter.loadProductDetails(productId, isClient);

        btnPuntuar.setOnClickListener(v -> {
            Log.d(TAG, "Launching PuntuarProductoActivity with Product ID: " + productId + ", User ID: " + userId);
            Intent intent = new Intent(DetalleProdActivity.this, PuntuarProdActivity.class);
            intent.putExtra("productId", productId);
            intent.putExtra("userId", userId);
            startActivity(intent);
        });

        btnComprar.setOnClickListener(v -> {
            Log.d(TAG, "Precio unitario antes de iniciar FinalizarCompraActivity: " + unitPrice);
            Intent intent = new Intent(DetalleProdActivity.this, FinalizarCompraActivity.class);
            intent.putExtra("productId", productId);
            intent.putExtra("userId", userId);
            intent.putExtra("precio_unitario", unitPrice);  // Clave consistente como String
            startActivity(intent);
        });
    }

    @Override
    public void showProductDetails(UVListarProdModel producto) {
        tvNombre.setText(producto.getNombre_producto());
        tvDescripcion.setText(producto.getDesc_producto());
        tvPrecio.setText("Precio: €" + producto.getPrecio());

        // Asignar el precio unitario como String
        unitPrice = producto.getPrecio();
        Log.d(TAG, "Precio unitario asignado en showProductDetails: " + unitPrice);

        Glide.with(this)
                .load(producto.getImagen_prod())
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                .into(ivImagen);

        if (isClient) {
            btnPuntuar.setVisibility(View.VISIBLE);
            btnComprar.setVisibility(View.VISIBLE);
        } else {
            btnPuntuar.setVisibility(View.GONE);
            btnComprar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(String message) {
        Log.e(TAG, "Error: " + message);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showBuyAndRateOptions() {
        btnPuntuar.setVisibility(View.VISIBLE);
        btnComprar.setVisibility(View.VISIBLE);
}
}