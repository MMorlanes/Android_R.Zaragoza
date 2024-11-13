// UCFiltradoCategAdapter.java
package com.example.r_zaragoza.UCFiltradoCateg.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.r_zaragoza.R;
import com.example.r_zaragoza.UVListarProd.model.UVListarProdModel;
import com.squareup.picasso.Picasso;
import java.util.List;

public class UCFiltradoCategAdapter extends RecyclerView.Adapter<UCFiltradoCategAdapter.ProductoViewHolder> {

    private List<UVListarProdModel> productos;

    public UCFiltradoCategAdapter(List<UVListarProdModel> productos) {
        this.productos = productos;
    }

    @Override
    public ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_producto_categoria, parent, false);
        return new ProductoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductoViewHolder holder, int position) {
        UVListarProdModel producto = productos.get(position);

        holder.nombre.setText(producto.getNombre_producto());
        holder.descripcion.setText(producto.getDesc_producto());
        holder.precio.setText("Precio: " + producto.getPrecio() + " €");

        // Cargar imagen con Picasso
        if (producto.getImagen_prod() != null && !producto.getImagen_prod().isEmpty()) {
            Picasso.get()
                    .load("URL_BASE_IMAGEN/" + producto.getImagen_prod())
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .into(holder.imagen);
        } else {
            holder.imagen.setImageResource(R.drawable.logo);
        }
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public void setProductos(List<UVListarProdModel> productos) {
        this.productos = productos;
        notifyDataSetChanged();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, descripcion, precio;
        ImageView imagen;

        public ProductoViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvProductoNombreCategoria);
            descripcion = itemView.findViewById(R.id.tvProductoDescripcionCategoria);
            precio = itemView.findViewById(R.id.tvProductoPrecioCategoria);
            imagen = itemView.findViewById(R.id.ivProductoImagenCategoria);
        }
    }
}
