package com.example.r_zaragoza.UCDetalleProd.view;

import android.os.Bundle;
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
import com.example.r_zaragoza.R;
import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;
import com.example.r_zaragoza.utils.RetrofitClient;
import com.example.r_zaragoza.utils.ApiService;

public class DetalleProdActivity extends AppCompatActivity implements DetalleProdContract.View {

    private TextView tvNombre, tvDescripcion, tvPrecio, tvStock;
    private ImageView ivImagen;
    private Button btnPuntuar, btnComprar;
    private DetalleProdPresenter presenter;
    private boolean isClient;

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

        int productId = getIntent().getIntExtra("productId", -1);
        isClient = getIntent().getBooleanExtra("isClient", false);

        if (productId != -1) {
            presenter.loadProductDetails(productId, isClient);
        } else {
            showError("Error: ID de producto no válido.");
        }

        // Muestra los botones solo si es cliente
        if (isClient) {
            btnPuntuar.setVisibility(View.VISIBLE);
            btnComprar.setVisibility(View.VISIBLE);
        } else {
            btnPuntuar.setVisibility(View.GONE);
            btnComprar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showProductDetails(UVListarProdModel producto) {
        tvNombre.setText(producto.getNombre_producto());
        tvDescripcion.setText(producto.getDesc_producto());
        tvPrecio.setText("Precio: €" + producto.getPrecio());

        Glide.with(this)
                .load(producto.getImagen_prod())
                .placeholder(R.drawable.logo) // Imagen predeterminada
                .error(R.drawable.logo)       // Imagen de error si falla
                .into(ivImagen);
    }


    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showBuyAndRateOptions() {
        btnPuntuar.setVisibility(View.VISIBLE);
        btnComprar.setVisibility(View.VISIBLE);
    }
}
