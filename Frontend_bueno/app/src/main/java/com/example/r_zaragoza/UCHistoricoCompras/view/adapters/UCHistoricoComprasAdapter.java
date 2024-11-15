package com.example.r_zaragoza.UCHistoricoCompras.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.r_zaragoza.R;
import com.example.r_zaragoza.UCHistoricoCompras.model.PedidoProd;
import com.example.r_zaragoza.UCHistoricoCompras.model.ProdPedido;

import java.util.List;

public class UCHistoricoComprasAdapter extends RecyclerView.Adapter<UCHistoricoComprasAdapter.PedidoViewHolder> {

    private List<PedidoProd> pedidos;

    public UCHistoricoComprasAdapter(List<PedidoProd> pedidos) {
        this.pedidos = pedidos;
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido, parent, false);
        return new PedidoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, int position) {
        PedidoProd pedido = pedidos.get(position);

        holder.fechaPedido.setText("Fecha: " + pedido.getFecha_compra());
        holder.precioTotal.setText("Total: €" + pedido.getPrecioTotal());

        holder.productosContainer.removeAllViews();

        for (ProdPedido producto : pedido.getProductos()) {
            View productoView = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.item_producto_pedido, holder.productosContainer, false);

            TextView nombreProducto = productoView.findViewById(R.id.tvProductoNombre);
            TextView precioUnitario = productoView.findViewById(R.id.tvPrecioUnitario);

            nombreProducto.setText(producto.getNombre());
            precioUnitario.setText("Precio: €" + producto.getPrecioUnitario());

            holder.productosContainer.addView(productoView);
        }
    }

    @Override
    public int getItemCount() { return pedidos.size(); }

    static class PedidoViewHolder extends RecyclerView.ViewHolder {
        TextView fechaPedido, precioTotal;
        ViewGroup productosContainer;

        public PedidoViewHolder(@NonNull View itemView) {
            super(itemView);
            fechaPedido = itemView.findViewById(R.id.tvFechaPedido);
            precioTotal = itemView.findViewById(R.id.tvPrecioTotal);
            productosContainer = itemView.findViewById(R.id.productosContainer);
        }
    }
}
