package com.example.r_zaragoza.UVdarAltaProd.model;

public class Product {
    private int idUsuario;
    private String nombreProducto;
    private String descProd;
    private String imagenProd;
    private double precio;
    private String categoria;

    public Product(int idUsuario, String nombreProducto, String descProd, String imagenProd, double precio, String categoria) {
        this.idUsuario = idUsuario;
        this.nombreProducto = nombreProducto;
        this.descProd = descProd;
        this.imagenProd = imagenProd;
        this.precio = precio;
        this.categoria = categoria;
    }

    // Getters y Setters
    public int getIdUsuario() { return idUsuario; }
    public String getNombreProducto() { return nombreProducto; }
    public String getDescProd() { return descProd; }
    public String getImagenProd() { return imagenProd; }
    public double getPrecio() { return precio; }
    public String getCategoria() { return categoria; }
}
