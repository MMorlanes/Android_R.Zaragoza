package com.example.r_zaragoza.UCTop10Products.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.r_zaragoza.R;
import com.example.r_zaragoza.UCTop10Products.view.adapter.Top10ProductAdapter;
import com.example.r_zaragoza.UCTop10Products.contracts.UCTop10ProductsContract;
import com.example.r_zaragoza.UCTop10Products.model.UCTop10ProductModel;
import com.example.r_zaragoza.UCTop10Products.presenter.UCTop10ProductPresenter;
import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;

import java.util.ArrayList;
import java.util.List;

public class Top10ProductActivity extends AppCompatActivity implements UCTop10ProductsContract.View {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Top10ProductAdapter adapter;
    private UCTop10ProductsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10_product);

        recyclerView = findViewById(R.id.recyclerViewTopProducts);
        progressBar = findViewById(R.id.progressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Top10ProductAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(adapter);

        presenter = new UCTop10ProductPresenter(this, new UCTop10ProductModel());
        presenter.requestTop10Products();
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
    public void displayProducts(List<UVListarProdModel> productos) {
        adapter = new Top10ProductAdapter(this, productos);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
