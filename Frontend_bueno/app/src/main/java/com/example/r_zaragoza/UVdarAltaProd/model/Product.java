package com.example.r_zaragoza.UVdarAltaProd.model;

public class Product {
    private int id_usuario;
    private String nombre_producto;
    private String desc_prod;
    private String imagen_prod;
    private double precio;
    private String categoria;

    public Product(int idUsuario, String nombreProducto, String descProd, String imagenProd, double precio, String categoria) {
        this.id_usuario = idUsuario;
        this.nombre_producto = nombreProducto;
        this.desc_prod = descProd;
        this.imagen_prod = imagenProd;
        this.precio = precio;
        this.categoria = categoria;
    }

    // Getters y Setters
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getDesc_prod() {
        return desc_prod;
    }

    public void setDesc_prod(String desc_prod) {
        this.desc_prod = desc_prod;
    }

    public String getImagen_prod() {
        return imagen_prod;
    }

    public void setImagen_prod(String imagen_prod) {
        this.imagen_prod = imagen_prod;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id_usuario=" + id_usuario +
                ", nombre_producto='" + nombre_producto + '\'' +
                ", desc_prod='" + desc_prod + '\'' +
                ", imagen_prod='" + imagen_prod + '\'' +
                ", precio=" + precio +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
