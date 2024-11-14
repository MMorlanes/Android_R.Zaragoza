package com.example.r_zaragoza.UVListarProd.view.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.r_zaragoza.R;
import com.example.r_zaragoza.UCDetalleProd.view.DetalleProdActivity;
import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private List<UVListarProdModel> productos;
    private OnProductoClickListener listener;

    public ProductoAdapter(List<UVListarProdModel> productos, OnProductoClickListener listener) {
        this.productos = productos;
        this.listener = listener;
    }

    @Override
    public ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_card_producto, parent, false);
        return new ProductoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductoViewHolder holder, int position) {
        UVListarProdModel producto = productos.get(position);

        Log.e("Adapter", "" + producto.getId_producto() + " " + producto.getNombre_producto());

        holder.nombre.setText("Nombre: " +producto.getNombre_producto());
        holder.precio.setText("Precio: " + producto.getPrecio() + " €");

        if (producto.getImagen_prod() != null && !producto.getImagen_prod().isEmpty()) {
            Picasso.get()
                    .load(producto.getImagen_prod())
                    .placeholder(R.drawable.toalla)
                    .error(R.drawable.logo)
                    .into(holder.imagen);
        } else {
            holder.imagen.setImageResource(R.drawable.logo);
        }

        // Agregar OnClickListener para abrir DetalleProdActivity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetalleProdActivity.class);
            intent.putExtra("productId", producto.getId_producto());
            intent.putExtra("isClient", false); // Cambiar según la lógica de tu aplicación
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public interface OnProductoClickListener {
        void onProductoClick(UVListarProdModel producto);
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, descripcion, precio;
        ImageView imagen;

        public ProductoViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvProductoNombre);
            descripcion = itemView.findViewById(R.id.tvProductoDescripcion);
            precio = itemView.findViewById(R.id.tvProductoPrecio);
            imagen = itemView.findViewById(R.id.ivProductoImagen);
        }
    }
}
