package com.example.r_zaragoza.UVListarProd.model;

public class UVListarProdModel {
    private int id_producto;
    private String nombre_producto;
    private String desc_producto;
    private String precio;
    private String imagen_prod;

    public String getImagen_prod() {
        return imagen_prod;
    }

    public void setImagen_prod(String imagen_prod) {
        this.imagen_prod = imagen_prod;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDesc_producto() {
        return desc_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.desc_producto = descripcion_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
}


