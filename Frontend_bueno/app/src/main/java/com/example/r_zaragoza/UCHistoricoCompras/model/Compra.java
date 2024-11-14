package com.example.r_zaragoza.UCHistoricoCompras.model;

public class Compra {
    private int pedido_id;
    private String fecha_compra;
    private String precio_total;
    private int producto_id;
    private String nombre;
    private int cantidad;
    private String precio_unitario;

    // Constructor
    public Compra(int pedido_id, String fecha_compra, String precio_total, int producto_id, String nombre, int cantidad, String precio_unitario) {
        this.pedido_id = pedido_id;
        this.fecha_compra = fecha_compra;
        this.precio_total = precio_total;
        this.producto_id = producto_id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }

    public int getPedido_id() {
        return pedido_id;
    }

    public String getFecha_compra() {
        return fecha_compra;
    }

    public String getPrecio_total() {
        return precio_total;
    }

    public int getProducto_id() {
        return producto_id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getPrecio_unitario() {
        return precio_unitario;
    }
}

