package com.example.r_zaragoza.utils;

public class Email {
    private String encabezado;
    private String cuerpo;
    private String destinatario;

    public Email(String encabezado, String cuerpo, String destinatario) {
        this.encabezado = encabezado;
        this.cuerpo = cuerpo;
        this.destinatario = destinatario;
    }

    public String getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
}
