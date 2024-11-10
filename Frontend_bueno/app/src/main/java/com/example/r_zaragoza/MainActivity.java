package com.example.r_zaragoza;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.r_zaragoza.LOGyREG.view.LoginActivity;
import com.example.r_zaragoza.LOGyREG.view.RegisterActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(view -> {
            showToast("Iniciar Sesión");
            startActivity(new Intent(this, LoginActivity.class));
        });

        btnRegister.setOnClickListener(view -> {
            showToast("Registrarse");
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}