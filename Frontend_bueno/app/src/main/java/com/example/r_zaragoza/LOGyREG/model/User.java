package com.example.r_zaragoza.LOGyREG.model;

public class User {
    private String username;
    private String email;
    private String password;
    private String rol;

    // Constructor para registro
    public User(String username, String email, String password, String rol) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    // Constructor para login
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    // ... otros getters y setters

    public String getPassword() {
        return password;
    }

    public String getRol() {
        return rol;
    }
}
