package com.example.r_zaragoza.UCTop10Sellers.view.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.r_zaragoza.R;
import com.example.r_zaragoza.UCTop10Sellers.model.Seller;

import java.util.List;

public class Top10SellersAdapter extends RecyclerView.Adapter<Top10SellersAdapter.SellerViewHolder> {

    private List<Seller> sellers;

    public Top10SellersAdapter(List<Seller> sellers) {
        this.sellers = sellers;
    }

    @Override
    public SellerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_top10_seller, parent, false);
        return new SellerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SellerViewHolder holder, int position) {
        Seller seller = sellers.get(position);
        holder.tvSellerName.setText("Usuario: " + seller.getUserName());
        holder.tvSalesCount.setText("Número de ventas: " + seller.getSalesCount());
    }

    @Override
    public int getItemCount() {
        return sellers.size();
    }

    public void updateData(List<Seller> newSellers) {
        sellers = newSellers;
        notifyDataSetChanged();
    }

    public static class SellerViewHolder extends RecyclerView.ViewHolder {
        TextView tvSellerName, tvSalesCount;

        public SellerViewHolder(View itemView) {
            super(itemView);
            tvSellerName = itemView.findViewById(R.id.tvSellerName);
            tvSalesCount = itemView.findViewById(R.id.tvSalesCount);
        }
    }
}
