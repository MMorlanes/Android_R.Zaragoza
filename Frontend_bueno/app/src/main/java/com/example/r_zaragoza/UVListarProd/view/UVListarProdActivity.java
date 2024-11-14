
package com.example.r_zaragoza.UVListarProd.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.r_zaragoza.R;
import com.example.r_zaragoza.UVListarProd.contracts.UVListarProdContract;
import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;
import com.example.r_zaragoza.UVListarProd.presenter.UVListarProdPresenter;
import com.example.r_zaragoza.UVListarProd.view.adapters.ProductoAdapter;
import com.example.r_zaragoza.utils.ApiService;
import com.example.r_zaragoza.utils.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UVListarProdActivity extends AppCompatActivity implements UVListarProdContract.View, ProductoAdapter.OnProductoClickListener {

    private RecyclerView recyclerView;
    private ProductoAdapter productoAdapter;
    private UVListarProdContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listarprod);

        recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar el presentador
        presenter = new UVListarProdPresenter(this);

        // Cargar los productos
        Log.d("ListarProductosActivity", "Cargando productos...");
        presenter.loadProductos();
    }

    @Override
    public int getVendedorId() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int vendedorId = sharedPreferences.getInt("userId", -1); // Devuelve -1 si no se encuentra el ID
        Log.d("ListarProductosActivity", "Vendedor ID: " + vendedorId);

        if (vendedorId == -1) {
            Log.e("ListarProductosActivity", "Vendedor ID no encontrado.");
        }

        return vendedorId;
    }

    @Override
    public void showProductos(List<UVListarProdModel> productos) {
        if (productos != null && !productos.isEmpty()) {
            productoAdapter = new ProductoAdapter(productos, this);
            recyclerView.setAdapter(productoAdapter);
        } else {
            showError("No se encontraron productos para mostrar.");
        }
    }

    @Override
    public void showError(String message) {
        Log.e("ListarProductosActivity", "Error: " + message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProductoClick(UVListarProdModel producto) {
        int idProducto = producto.getId_producto();
        loadProductoDetalle(idProducto);
    }

    private void loadProductoDetalle(int idProducto) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<UVListarProdModel> call = apiService.getProductoDetalle(idProducto);

    }
}
