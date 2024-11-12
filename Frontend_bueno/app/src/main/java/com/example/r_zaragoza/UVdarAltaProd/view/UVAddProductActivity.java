package com.example.r_zaragoza.UVdarAltaProd.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.r_zaragoza.R;
import com.example.r_zaragoza.UVMainActivity;
import com.example.r_zaragoza.UVdarAltaProd.contracts.UVAddProductContract;
import com.example.r_zaragoza.UVdarAltaProd.presenter.UVAddProductPresenter;

public class UVAddProductActivity extends AppCompatActivity implements UVAddProductContract.View {
    private EditText etProductName, etProductDescription, etProductImage, etProductPrice, etProductCategory;
    private Button btnAddProduct;
    private UVAddProductContract.Presenter presenter;
    private int userId; // ID del usuario logueado (vendedor)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        // Inicializar vistas
        etProductName = findViewById(R.id.etProductName);
        etProductDescription = findViewById(R.id.etProductDescription);
        etProductImage = findViewById(R.id.etProductImage);
        etProductPrice = findViewById(R.id.etProductPrice);
        etProductCategory = findViewById(R.id.etProductCategory);
        btnAddProduct = findViewById(R.id.btnAddProduct);

        // Inicializar presentador
        presenter = new UVAddProductPresenter(this);

        // Asignar un ID de usuario estático (similar al primer archivo)
        userId = 1;  // Esto es estático para simplificar, luego puedes cambiarlo para obtenerlo dinámicamente

        if (userId == -1) {
            // Si no se ha encontrado el ID del usuario logueado, mostrar mensaje y salir
            Toast.makeText(this, "No se ha detectado un usuario logueado", Toast.LENGTH_LONG).show();
            finish(); // Cerrar la actividad si no hay usuario logueado
        }

        // Configurar el evento del botón de añadir producto
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddProductClicked();  // Llamada al presentador para agregar el producto
            }
        });
    }

    @Override
    public int getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("userId", -1); // ID del usuario almacenado tras login
    }


    @Override
    public String getProductName() {
        return etProductName.getText().toString().trim();
    }

    @Override
    public String getProductDescription() {
        return etProductDescription.getText().toString().trim();
    }

    @Override
    public String getProductImage() {
        return etProductImage.getText().toString().trim();
    }

    @Override
    public String getProductPrice() {
        return etProductPrice.getText().toString().trim();
    }

    @Override
    public String getProductCategory() {
        return etProductCategory.getText().toString().trim();
    }

    @Override
    public void showProductAddedSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish(); // Regresar a la pantalla anterior
    }

    @Override
    public void showProductAddedError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void RedirectToUVMenu() {
        // Cerrar la actividad actual
        finish();
        // Redirigir al menú del vendedor
        Intent intent = new Intent(UVAddProductActivity.this, UVMainActivity.class);
        startActivity(intent);
    }
}
