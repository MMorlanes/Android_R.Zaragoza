package com.example.r_zaragoza.UCFinalizarCompra.model;

public class ConfirmPurchaseRequest {
    private int id_usuario;
    private int producto_id; // Cambiado de id_producto a producto_id
    private double precio_unitario;

    public ConfirmPurchaseRequest(int id_usuario, int producto_id, double precio_unitario) {
        this.id_usuario = id_usuario;
        this.producto_id = producto_id;
        this.precio_unitario = precio_unitario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getProducto_id() { // Cambiado de getId_producto a getProducto_id
        return producto_id;
    }

    public void setProducto_id(int producto_id) { // Cambiado de setId_producto a setProducto_id
        this.producto_id = producto_id;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
}
