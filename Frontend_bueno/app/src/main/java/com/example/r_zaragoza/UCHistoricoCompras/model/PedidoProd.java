package com.example.r_zaragoza.UCHistoricoCompras.model;

import java.util.List;

public class PedidoProd {
    private int pedidoId;
    private String fecha_compra;
    private String precioTotal;
    private List<ProdPedido> productos;

    public PedidoProd(int pedidoId, String fecha_compra, String precioTotal, List<ProdPedido> productos) {
        this.pedidoId = pedidoId;
        this.fecha_compra = fecha_compra;
        this.precioTotal = precioTotal;
        this.productos = productos;
    }

    public int getPedidoId() { return pedidoId; }
    public String getFecha_compra() { return fecha_compra; }
    public String getPrecioTotal() { return precioTotal; }
    public List<ProdPedido> getProductos() { return productos; }
}
