package com.example.r_zaragoza.UCTop10Products.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.r_zaragoza.R;
import com.example.r_zaragoza.UCDetalleProd.view.DetalleProdActivity;
import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;

import java.util.List;

public class Top10ProductAdapter extends RecyclerView.Adapter<Top10ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<UVListarProdModel> productos;

    public Top10ProductAdapter(Context context, List<UVListarProdModel> productos) {
        this.context = context;
        this.productos = productos;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_top10_products, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        UVListarProdModel producto = productos.get(position);

        holder.nombre.setText(producto.getNombre_producto());
        holder.precio.setText("Precio: " + producto.getPrecio() + "€");

        Glide.with(context)
                .load(producto.getImagen_prod())
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                .into(holder.imagen);

        // Agregar OnClickListener para abrir DetalleProdActivity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetalleProdActivity.class);
            intent.putExtra("productId", producto.getId_producto());
            intent.putExtra("isClient", true); // Cambiar según la lógica de tu aplicación
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, precio;
        ImageView imagen;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvNombreProducto);
            precio = itemView.findViewById(R.id.tvPrecioProducto);
            imagen = itemView.findViewById(R.id.ivImagenProducto);
        }
    }
}
