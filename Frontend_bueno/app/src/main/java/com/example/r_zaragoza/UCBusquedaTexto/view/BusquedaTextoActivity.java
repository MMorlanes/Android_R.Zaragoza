package com.example.r_zaragoza.UCBusquedaTexto.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.r_zaragoza.UCBusquedaTexto.contracts.BusquedaTextoContract;
import com.example.r_zaragoza.UCBusquedaTexto.model.BusquedaTextoModel;
import com.example.r_zaragoza.UCBusquedaTexto.presenter.BusquedaTextoPresenter;
import com.example.r_zaragoza.UCBusquedaTexto.view.adapters.BusquedaTextoAdapter;
import com.example.r_zaragoza.R;
import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;
import com.example.r_zaragoza.utils.ApiService;
import com.example.r_zaragoza.utils.RetrofitClient;

import java.util.List;

public class BusquedaTextoActivity extends AppCompatActivity implements BusquedaTextoContract.View {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private BusquedaTextoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_texto);

        recyclerView = findViewById(R.id.recyclerViewProducts);
        progressBar = findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String query = getIntent().getStringExtra("query");

        // Obtén la instancia de ApiService a través de RetrofitClient
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        presenter = new BusquedaTextoPresenter(this, new BusquedaTextoModel(apiService));
        presenter.searchProducts(query);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProducts(List<UVListarProdModel> productos) {
        BusquedaTextoAdapter adapter = new BusquedaTextoAdapter(productos);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
