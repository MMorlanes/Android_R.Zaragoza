package com.example.r_zaragoza;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.r_zaragoza.UCBusquedaTexto.view.BusquedaTextoActivity;
import com.example.r_zaragoza.UCFiltradoCateg.view.UCFiltradoCategActivity;
import com.example.r_zaragoza.UCHistoricoCompras.view.UCHistoricoComprasActivity;
import com.example.r_zaragoza.UCTop10Products.view.Top10ProductActivity;
import com.example.r_zaragoza.UCTop10Sellers.view.Top10SellersActivity;

public class UCMainActivity extends AppCompatActivity {
    private EditText searchBar;
    private Button searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uc);
        searchBar = findViewById(R.id.search_bar);
        searchButton = findViewById(R.id.search_button);

        Button btnTop10Sellers = findViewById(R.id.btnTop10Sellers);
        Button btnTopRatedProducts = findViewById(R.id.btnTopRatedProducts);
        // Categorias
        Button btnCategoryAccessories = findViewById(R.id.btnCategoryAccessories);
        Button btnCategorySummer = findViewById(R.id.btnCategorySummer);
        Button btnCategoryClothing = findViewById(R.id.btnCategoryClothing);

        Button btnPurchaseHistory = findViewById(R.id.btnPurchaseHistory);

        btnTop10Sellers.setOnClickListener(view -> {
            showToast("Mostrando los 10 propietarios con mas ventas");
            startActivity(new Intent(this, Top10SellersActivity.class));  // Actividad para dar de alta productos
        });

        btnTopRatedProducts.setOnClickListener(view -> {
            showToast("Mostrando los 10 Productos mejor puntuados");
            startActivity(new Intent(this, Top10ProductActivity.class));  // Actividad para ver listado de productos
        });

        // Categorias
        btnCategoryAccessories.setOnClickListener(view -> {
            showToast("Ver Listado de Productos");
            startActivityWithCategory("accesorio");
        });

        btnCategorySummer.setOnClickListener(view -> {
            showToast("Ver Listado de Productos");
            startActivityWithCategory("verano");
        });

        btnCategoryClothing.setOnClickListener(view -> {
            showToast("Ver Listado de Productos");
            startActivityWithCategory("ropa");
        });
        // Fin Categorias

        searchButton.setOnClickListener(v -> {
            String query = searchBar.getText().toString().trim();
            if (!query.isEmpty()) {
                Intent intent = new Intent(this, BusquedaTextoActivity.class);
                intent.putExtra("query", query);
                startActivity(intent);
         }
});

        btnPurchaseHistory.setOnClickListener(view -> {
            showToast("Obteniendo el historico de compra...");
            startActivity(new Intent(this, UCHistoricoComprasActivity.class));  // Actividad para ver listado de productos
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void startActivityWithCategory(String categoria) {
        Intent intent = new Intent(this, UCFiltradoCategActivity.class);
        intent.putExtra("categoria_nombre", categoria);
        startActivity(intent);
    }
}
