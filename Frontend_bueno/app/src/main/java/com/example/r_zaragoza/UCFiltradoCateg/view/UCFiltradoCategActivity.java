package com.example.r_zaragoza.UCFiltradoCateg.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.r_zaragoza.UCFiltradoCateg.presenter.UCFiltradoCategPresenter;
import com.example.r_zaragoza.UCFiltradoCateg.contracts.UCFiltradoCategContract;
import com.example.r_zaragoza.R;
import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;
import com.example.r_zaragoza.UCFiltradoCateg.view.adapter.UCFiltradoCategAdapter;

import java.util.ArrayList;
import java.util.List;

public class UCFiltradoCategActivity extends AppCompatActivity implements UCFiltradoCategContract.View {

    private TextView textViewCategoriaNombre;
    private RecyclerView recyclerViewProductosCategoria;
    private UCFiltradoCategAdapter productoCategoriaAdapter;
    private UCFiltradoCategPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_categoria);

        // Inicializar vistas
        textViewCategoriaNombre = findViewById(R.id.textViewCategoriaNombre);
        recyclerViewProductosCategoria = findViewById(R.id.recyclerViewProductosCategoria);

        // Configuración del RecyclerView
        recyclerViewProductosCategoria.setLayoutManager(new LinearLayoutManager(this));
        productoCategoriaAdapter = new UCFiltradoCategAdapter(new ArrayList<>());
        recyclerViewProductosCategoria.setAdapter(productoCategoriaAdapter);

        // Obtener el nombre de la categoría desde el Intent
        String categoriaNombre = getIntent().getStringExtra("categoria_nombre");

        // Validar el nombre de la categoría
        if (categoriaNombre != null && !categoriaNombre.isEmpty()) {
            // Mostrar el nombre de la categoría
            textViewCategoriaNombre.setText("Categoría: " + categoriaNombre);

            // Inicializar el presentador usando el nombre de la categoría
            presenter = new UCFiltradoCategPresenter(this, categoriaNombre);
            presenter.loadCategorias();  // Llamada a cargar productos
        } else {
            // Mostrar error si el nombre de la categoría no está disponible
            textViewCategoriaNombre.setText("Categoría desconocida");
            Toast.makeText(this, "Error: Categoría no especificada", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showCategorias(List<UVListarProdModel> productos) {
        Log.d("UCFiltradoCategActivity", "Mostrando productos: " + productos.size());
        if (productos != null && !productos.isEmpty()) {
            productoCategoriaAdapter.setProductos(productos);  // Actualiza los productos en el adapter
        } else {
            Toast.makeText(this, "No hay productos en esta categoría", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showError(String message) {
        Log.e("UCFiltradoCategActivity", "Error recibido: " + message);
        Toast.makeText(this, "Error: " + message, Toast.LENGTH_SHORT).show();
    }

}
