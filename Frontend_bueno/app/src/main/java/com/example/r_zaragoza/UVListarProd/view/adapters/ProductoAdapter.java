package com.example.r_zaragoza.UVListarProd.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.r_zaragoza.R;
import com.example.r_zaragoza.UVListarProd.model.Producto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private List<Producto> productos;

    public ProductoAdapter(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_card_producto, parent, false); // Asegúrate de que este layout tiene los IDs correctos
        return new ProductoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductoViewHolder holder, int position) {
        Producto producto = productos.get(position);

        // Asignar los datos a las vistas
        holder.nombre.setText(producto.getNombre_producto());
        holder.descripcion.setText(producto.getDescripcion_producto());

        // Agregar "€" al precio y "ud" al stock
        holder.precio.setText("Precio: " + producto.getPrecio() + " €");

        // Cargar la imagen si está disponible (usando Picasso)
        if (producto.getImagen_prod() != null && !producto.getImagen_prod().isEmpty()) {
            Picasso.get()
                    .load(producto.getImagen_prod())  // URL de la imagen del producto
                    .placeholder(R.drawable.logo) // Imagen por defecto mientras se carga
                    .error(R.drawable.logo)       // Imagen por defecto si falla la carga
                    .into(holder.imagen);            // Asignar la imagen al ImageView del ViewHolder
        } else {
            // Si no hay URL de imagen, muestra la imagen por defecto
            holder.imagen.setImageResource(R.drawable.logo);  // Imagen por defecto
        }
    }


    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, descripcion, precio, stock;
        ImageView imagen;

        public ProductoViewHolder(View itemView) {
            super(itemView);
            // Asegúrate de que estos IDs coincidan con los que tienes en el archivo item_producto.xml
            nombre = itemView.findViewById(R.id.tvProductoNombre);
            descripcion = itemView.findViewById(R.id.tvProductoDescripcion);
            precio = itemView.findViewById(R.id.tvProductoPrecio);
            imagen = itemView.findViewById(R.id.ivProductoImagen);
        }
    }
}