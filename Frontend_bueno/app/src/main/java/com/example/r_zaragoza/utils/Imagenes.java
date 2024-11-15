package com.example.r_zaragoza.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Imagenes extends Thread{
    private String url;
    private ImageView imageView;
    private static Bitmap bitmap;

    public Imagenes(Builder builder) {
        this.url = builder.url;
        this.imageView = builder.imageView;
    }

    @Override
    public void run() {
        load();
    }

    public void load() {
        new Thread(() -> {
            try {
                URL url = new URL(this.url); // Usa la URL proporcionada
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream input = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(input);

                // Actualizar el ImageView en el hilo principal
                imageView.post(() -> imageView.setImageBitmap(bitmap));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static class Builder {
        private String url;
        private ImageView imageView;

        public Builder(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        public Imagenes build() {
            return new Imagenes (this);
        }
    }
}