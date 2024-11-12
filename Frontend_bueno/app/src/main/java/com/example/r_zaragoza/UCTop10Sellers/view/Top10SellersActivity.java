// Top10SellersActivity.java
package com.example.r_zaragoza.UCTop10Sellers.view;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.r_zaragoza.UCTop10Sellers.Adapter.Top10SellersAdapter;
import com.example.r_zaragoza.R;
import com.example.r_zaragoza.UCTop10Sellers.contracts.UCTop10SellersContract;
import com.example.r_zaragoza.UCTop10Sellers.model.Seller;
import com.example.r_zaragoza.UCTop10Sellers.presenter.UCTop10SellersPresenter;

import java.util.ArrayList;
import java.util.List;

public class Top10SellersActivity extends AppCompatActivity implements UCTop10SellersContract.View {

    private RecyclerView rvTop10Sellers;
    private Top10SellersAdapter adapter;
    private UCTop10SellersPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10_sellers);

        rvTop10Sellers = findViewById(R.id.rvTop10Sellers);
        rvTop10Sellers.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Top10SellersAdapter(new ArrayList<>());
        rvTop10Sellers.setAdapter(adapter);

        // Inicializamos el presenter y cargamos los vendedores
        presenter = new UCTop10SellersPresenter(this);
        presenter.loadTopSellers();
    }

    @Override
    public void showTopSellers(List<Seller> sellers) {
        adapter.updateData(sellers);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
