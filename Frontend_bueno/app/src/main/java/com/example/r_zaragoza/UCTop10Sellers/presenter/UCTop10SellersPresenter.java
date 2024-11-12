package com.example.r_zaragoza.UCTop10Sellers.presenter;

import android.util.Log;

import com.example.r_zaragoza.UCTop10Sellers.contracts.UCTop10SellersContract;
import com.example.r_zaragoza.UCTop10Sellers.model.Seller;
import com.example.r_zaragoza.utils.ApiService;
import com.example.r_zaragoza.utils.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UCTop10SellersPresenter implements UCTop10SellersContract.Presenter {
    private UCTop10SellersContract.View view;
    private ApiService apiService;
    int userId;
    String userName;
    int salesCount;

    public UCTop10SellersPresenter(UCTop10SellersContract.View view) {
        this.view = view;
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }

    @Override
    public void loadTopSellers() {
        Seller seller = new Seller(userId, userName, salesCount);

        Call<ResponseBody> call = apiService.top_sellers(seller);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        String responseBodyString = response.body().string();
                        JSONArray jsonArray = new JSONArray(responseBodyString);
                        List<Seller> sellers = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonSeller = jsonArray.getJSONObject(i);
                            int userId = jsonSeller.getInt("userId");
                            String userName = jsonSeller.getString("userName");
                            int salesCount = jsonSeller.getInt("salesCount");
                            sellers.add(new Seller(userId, userName, salesCount));
                        }

                        view.showTopSellers(sellers);
                    } catch (Exception e) {
                        Log.e("TopSellersPresenter", "Error al procesar la respuesta", e);
                        view.showError("Error al procesar la respuesta del servidor");
                    }
                } else {
                    view.showError("Error al obtener los vendedores");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showError("Error de conexión: " + t.getMessage());
            }
        });
    }
}
