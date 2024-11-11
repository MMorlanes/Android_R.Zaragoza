package com.example.r_zaragoza.UVdarAltaProd.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.r_zaragoza.R;
//import com.example.r_zaragoza.VendedorAltaProducto.view.VendedorAltaProductoActivity; // Asegúrate de tener esta actividad
//import com.example.r_zaragoza.VendedorListado.view.VendedorListadoActivity; // Asegúrate de tener esta actividad

public class UVMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uv);

        Button btnAltaProducto = findViewById(R.id.btnAltaProducto);
        Button btnListadoProductos = findViewById(R.id.btnListadoProductos);

        btnAltaProducto.setOnClickListener(view -> {
            showToast("Dar de Alta Producto");
            startActivity(new Intent(this, UVAddProductActivity.class));  // Actividad para dar de alta productos
        });

        /*btnListadoProductos.setOnClickListener(view -> {
            showToast("Ver Listado de Productos");
            startActivity(new Intent(this, VendedorListadoActivity.class));  // Actividad para ver listado de productos
        });*/
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
