package com.example.r_zaragoza.UVListarProd.model;

import java.util.List;

public class ProductosResponse {
    private List<UVListarProdModel> productos;

    public List<UVListarProdModel> getProductos() {
        return productos;
    }

    public void setProductos(List<UVListarProdModel> productos) {
        this.productos = productos;
    }
}
