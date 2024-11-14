package com.example.r_zaragoza.UCPuntuarProd.view;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.r_zaragoza.UCPuntuarProd.contracts.UCPuntuarProdContract;
import com.example.r_zaragoza.UCPuntuarProd.model.PuntuarProdModel;
import com.example.r_zaragoza.UCPuntuarProd.presenter.PuntuarProdPresenter;
import com.example.r_zaragoza.R;
import com.example.r_zaragoza.utils.ApiService;
import com.example.r_zaragoza.utils.RetrofitClient;


public class PuntuarProdActivity extends AppCompatActivity implements UCPuntuarProdContract.View {

    private RatingBar rbPuntuacion;
    private EditText etComentario;
    private Button btnEnviarPuntuacion;
    private PuntuarProdPresenter presenter;
    private int productId;
    private int userId;

    private static final String TAG = "PuntuarProductoActivity";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuar_producto);

        rbPuntuacion = findViewById(R.id.rbPuntuacion);
        etComentario = findViewById(R.id.etComentario);
        btnEnviarPuntuacion = findViewById(R.id.btnEnviarPuntuacion);

        // Crear instancia del presenter
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        presenter = new PuntuarProdPresenter(this, new PuntuarProdModel(apiService, this)); // Añadimos `this` para el contexto


        // Obtener IDs de intent
        productId = getIntent().getIntExtra("productId", -1);
        userId = getIntent().getIntExtra("userId", -1);

        Log.d(TAG, "onCreate: Product ID: " + productId + ", User ID: " + userId);

        btnEnviarPuntuacion.setOnClickListener(v -> {
            int puntuacion = (int) rbPuntuacion.getRating();
            String comentario = etComentario.getText().toString();

            Log.d(TAG, "onClick: Puntuación: " + puntuacion + ", Comentario: " + comentario);

            if (userId != -1 && productId != -1) {
                presenter.submitRating(productId, userId, puntuacion, comentario);
            } else {
                Log.e(TAG, "Usuario o Producto ID inválido.");
                Toast.makeText(this, "Error al obtener datos de usuario o producto", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void showLoading() {
        btnEnviarPuntuacion.setEnabled(false);
    }

    @Override
    public void hideLoading() {
        btnEnviarPuntuacion.setEnabled(true);
    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
