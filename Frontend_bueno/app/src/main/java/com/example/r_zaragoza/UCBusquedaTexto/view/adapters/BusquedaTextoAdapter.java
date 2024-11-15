package com.example.r_zaragoza.UCBusquedaTexto.view.adapters;

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
import com.example.r_zaragoza.utils.Imagenes;

import java.util.List;

public class BusquedaTextoAdapter extends RecyclerView.Adapter<BusquedaTextoAdapter.ProductViewHolder> {

    private List<UVListarProdModel> productos;

    public BusquedaTextoAdapter(List<UVListarProdModel> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto_busqueda, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        UVListarProdModel producto = productos.get(position);
        holder.productName.setText(producto.getNombre_producto());
        holder.productDescription.setText(producto.getDesc_producto());
        holder.productPrice.setText(String.format("$%s", producto.getPrecio()));

        new Imagenes.Builder(producto.getImagen_prod(), holder.productImage).build().start();

        // Configurar OnClickListener para abrir DetalleProdActivity al hacer clic en el producto
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetalleProdActivity.class);
            intent.putExtra("productId", producto.getId_producto());  // Pasa el ID del producto
            intent.putExtra("isClient", true);  // Cambia según la lógica de tu aplicación
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productDescription, productPrice;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.ivProductoImagen);
            productName = itemView.findViewById(R.id.tvProductoNombre);
            productDescription = itemView.findViewById(R.id.tvProductoDescripcion);
            productPrice = itemView.findViewById(R.id.tvProductoPrecio);
        }
    }
}
