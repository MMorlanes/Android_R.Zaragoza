// Top10SellersAdapter.java
package com.example.r_zaragoza.UCTop10Sellers.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.r_zaragoza.R;
import com.example.r_zaragoza.UCTop10Sellers.model.Seller;
import java.util.List;

public class Top10SellersAdapter extends RecyclerView.Adapter<Top10SellersAdapter.SellerViewHolder> {
    private List<Seller> sellersList;

    public Top10SellersAdapter(List<Seller> sellersList) {
        this.sellersList = sellersList;
    }

    @NonNull
    @Override
    public SellerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top10_seller, parent, false);
        return new SellerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SellerViewHolder holder, int position) {
        Seller seller = sellersList.get(position);
        holder.tvUserName.setText(seller.getUserName());
        holder.tvSalesCount.setText(String.valueOf(seller.getSalesCount()));
    }

    @Override
    public int getItemCount() {
        return sellersList.size();
    }

    public void updateData(List<Seller> newSellersList) {
        this.sellersList = newSellersList;
        notifyDataSetChanged();
    }

    static class SellerViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserName, tvSalesCount;

        SellerViewHolder(View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvSellerName);
            tvSalesCount = itemView.findViewById(R.id.tvSalesCount);
        }
    }
}
