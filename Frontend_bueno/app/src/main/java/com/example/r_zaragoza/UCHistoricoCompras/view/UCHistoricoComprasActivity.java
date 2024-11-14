package com.example.r_zaragoza.UCHistoricoCompras.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.r_zaragoza.UCHistoricoCompras.contracts.UCHistoricoComprasContract;
import com.example.r_zaragoza.UCHistoricoCompras.model.UCHistoricoComprasModel;
import com.example.r_zaragoza.UCHistoricoCompras.presenter.UCHistoricoComprasPresenter;
import com.example.r_zaragoza.R;
import com.example.r_zaragoza.UCHistoricoCompras.model.PedidoProd;
import com.example.r_zaragoza.UCHistoricoCompras.view.adapters.UCHistoricoComprasAdapter;
import com.example.r_zaragoza.utils.RetrofitClient;
import com.example.r_zaragoza.utils.ApiService;

import java.util.List;

public class UCHistoricoComprasActivity extends AppCompatActivity implements UCHistoricoComprasContract.View {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private UCHistoricoComprasPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_compras);

        recyclerView = findViewById(R.id.recyclerViewHistory);
        progressBar = findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener el userId desde SharedPreferences con el nombre correcto
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", -1);

        if (userId == -1) {
            Toast.makeText(this, "Error: No se encontró el ID del usuario", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        presenter = new UCHistoricoComprasPresenter(this, new UCHistoricoComprasModel(apiService));
        presenter.loadUserHistory(userId);
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
    public void showHistory(List<PedidoProd> historial) {
        UCHistoricoComprasAdapter adapter = new UCHistoricoComprasAdapter(historial);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
