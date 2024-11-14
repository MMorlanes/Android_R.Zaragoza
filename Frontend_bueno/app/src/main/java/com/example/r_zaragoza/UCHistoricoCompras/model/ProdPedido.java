package com.example.r_zaragoza.UCHistoricoCompras.model;

public class ProdPedido {
    private String nombre;
    private int cantidad;
    private String precioUnitario;

    public ProdPedido(String nombre, int cantidad, String precioUnitario) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public String getPrecioUnitario() { return precioUnitario; }
}
