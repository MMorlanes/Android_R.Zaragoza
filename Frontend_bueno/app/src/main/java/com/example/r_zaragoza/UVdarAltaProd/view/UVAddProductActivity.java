package com.example.r_zaragoza.UVdarAltaProd.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.r_zaragoza.R;
import com.example.r_zaragoza.UVdarAltaProd.contracts.UVAddProductContract;
import com.example.r_zaragoza.UVdarAltaProd.presenter.UVAddProductPresenter;

public class UVAddProductActivity extends AppCompatActivity implements UVAddProductContract.View {
    private EditText etProductName, etProductDescription, etProductImage, etProductPrice, etProductCategory;
    private Button btnAddProduct;
    private UVAddProductContract.Presenter presenter;

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

        // Configurar evento del botón de añadir producto
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddProductClicked();
            }
        });
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
        finish(); // Opcional: Regresa a la pantalla anterior
    }

    @Override
    public void showProductAddedError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
