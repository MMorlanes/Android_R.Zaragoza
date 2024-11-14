package com.example.r_zaragoza.UVListarProd.model;

import com.google.gson.annotations.SerializedName;

public class UVListarProdModel {

    @SerializedName("id_producto")
    private int id_producto;

    @SerializedName("nombre")
    private String nombre_producto; // Mapeado con el campo "nombre" del JSON

    @SerializedName("descripcion")
    private String desc_producto; // Mapeado con el campo "descripcion" del JSON

    @SerializedName("precio")
    private String precio;

    @SerializedName("imagen_url")
    private String imagen_prod; // Mapeado con el campo "imagen_url" del JSON

    @SerializedName("categoria")
    private String categoria;

    // Getters y setters
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

    public void setDesc_producto(String desc_producto) {
        this.desc_producto = desc_producto;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "UVListarProdModel{" +
                "id_producto=" + id_producto +
                ", nombre_producto='" + nombre_producto + '\'' +
                ", desc_producto='" + desc_producto + '\'' +
                ", precio='" + precio + '\'' +
                ", imagen_prod='" + imagen_prod + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
