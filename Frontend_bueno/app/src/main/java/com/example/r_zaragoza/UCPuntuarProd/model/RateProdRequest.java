package com.example.r_zaragoza.UCPuntuarProd.model;

public class RateProdRequest {
    private int id_producto;
    private int id_usuario;
    private int puntuacion;
    private String comentario;

    public RateProdRequest(int id_producto, int id_usuario, int puntuacion, String comentario) {
        this.id_producto = id_producto;
        this.id_usuario = id_usuario;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
    }

    // Getters y setters si son necesarios
}

