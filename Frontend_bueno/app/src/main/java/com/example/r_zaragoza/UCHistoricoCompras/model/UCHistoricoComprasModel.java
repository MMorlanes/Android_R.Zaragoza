package com.example.r_zaragoza.UCHistoricoCompras.model;

import com.example.r_zaragoza.UCHistoricoCompras.contracts.UCHistoricoComprasContract;
import com.example.r_zaragoza.utils.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UCHistoricoComprasModel implements UCHistoricoComprasContract.Model {

    private ApiService apiService;

    public UCHistoricoComprasModel(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void fetchUserHistory(int userId, OnFinishedListener listener) {
        apiService.getUserHistory(userId).enqueue(new Callback<List<Compra>>() {
            @Override
            public void onResponse(Call<List<Compra>> call, Response<List<Compra>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<PedidoProd> historialAgrupado = agruparPorPedido(response.body());
                    listener.onFinished(historialAgrupado);
                } else {
                    listener.onFailure("No se pudo obtener el historial.");
                }
            }

            @Override
            public void onFailure(Call<List<Compra>> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }

    // Método para agrupar las compras por pedido_id
    private List<PedidoProd> agruparPorPedido(List<Compra> compras) {
        Map<Integer, PedidoProd> pedidosMap = new HashMap<>();
        for (Compra compra : compras) {
            int pedidoId = compra.getPedido_id();
            PedidoProd pedido = pedidosMap.get(pedidoId);

            // Si el pedido aún no existe en el mapa, lo creamos y lo agregamos
            if (pedido == null) {
                pedido = new PedidoProd(
                        compra.getPedido_id(),
                        compra.getFecha_compra(),
                        compra.getPrecio_total(),
                        new ArrayList<>()
                );
                pedidosMap.put(pedidoId, pedido);
            }

            // Añadir el producto actual al pedido correspondiente
            pedido.getProductos().add(new ProdPedido(
                    compra.getNombre(),
                    compra.getCantidad(),
                    compra.getPrecio_unitario()
            ));
        }
        return new ArrayList<>(pedidosMap.values()); // Retornar lista de pedidos agrupados
    }
}
