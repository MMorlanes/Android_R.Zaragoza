package com.example.r_zaragoza;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.r_zaragoza.UCTop10Products.view.Top10ProductActivity;
import com.example.r_zaragoza.UCTop10Sellers.view.Top10SellersActivity;


public class UCMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uc);

        Button btnTop10Sellers = findViewById(R.id.btnTop10Sellers);
        Button btnRateItem = findViewById(R.id.btnRateItem);
        Button btnTopRatedProducts = findViewById(R.id.btnTopRatedProducts);
        Button btnFilterCategories = findViewById(R.id.btnFilterCategories);
        Button btnFilterByText = findViewById(R.id.btnFilterByText);
        Button btnViewItemDetails = findViewById(R.id.btnViewItemDetails);
        Button btnConfirmPurchase = findViewById(R.id.btnConfirmPurchase);
        Button btnPurchaseHistory = findViewById(R.id.btnPurchaseHistory);


        btnTop10Sellers.setOnClickListener(view -> {
            showToast("Mostrando los 10 propietarios con mas ventas");
            startActivity(new Intent(this, Top10SellersActivity.class));  // Actividad para dar de alta productos
        });

        /*btnRateItem.setOnClickListener(view -> {
            showToast("Ver Listado de Productos");
            startActivity(new Intent(this, UVListarProdActivity.class));  // Actividad para ver listado de productos
        });*/
        /*
        btnTopRatedProducts.setOnClickListener(view -> {
            showToast("Dar de Alta Producto");
            startActivity(new Intent(this, Top10ProductActivity.class));  // Actividad para dar de alta productos
        });*/

        /*btnFilterCategories.setOnClickListener(view -> {
            showToast("Ver Listado de Productos");
            startActivity(new Intent(this, UVListarProdActivity.class));  // Actividad para ver listado de productos
        });
        btnFilterByText.setOnClickListener(view -> {
            showToast("Dar de Alta Producto");
            startActivity(new Intent(this, UVAddProductActivity.class));  // Actividad para dar de alta productos
        });

        btnViewItemDetails.setOnClickListener(view -> {
            showToast("Ver Listado de Productos");
            startActivity(new Intent(this, UVListarProdActivity.class));  // Actividad para ver listado de productos
        });
        btnConfirmPurchase.setOnClickListener(view -> {
            showToast("Dar de Alta Producto");
            startActivity(new Intent(this, UVAddProductActivity.class));  // Actividad para dar de alta productos
        });

        btnPurchaseHistory.setOnClickListener(view -> {
            showToast("Ver Listado de Productos");
            startActivity(new Intent(this, UVListarProdActivity.class));  // Actividad para ver listado de productos
        });*/
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
