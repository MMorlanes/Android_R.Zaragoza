package com.example.r_zaragoza.LOGyREG.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.r_zaragoza.LOGyREG.contracts.LoginContract;
import com.example.r_zaragoza.LOGyREG.presenter.LoginPresenter;
import com.example.r_zaragoza.MainActivity;
import com.example.r_zaragoza.R;
import com.example.r_zaragoza.UCMainActivity;
import com.example.r_zaragoza.UVMainActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private EditText etEmail, etPassword;
    private Button btnLogin, btnRegister;
    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        presenter = new LoginPresenter(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onLoginClicked();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public String getEmail() {
        return etEmail.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString().trim();
    }

    @Override
    public void showLoginSuccess(String message, String role) {
        new AlertDialog.Builder(this)
                .setTitle("Login Exitoso")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();

                    // Redirigir al usuario basado en el rol devuelto por el backend
                    Log.d("LoginActivity", "Rol del usuario: " + role);

                    if ("vendedor".equalsIgnoreCase(role.trim())) {
                        Intent intent = new Intent(LoginActivity.this, UVMainActivity.class);
                        startActivity(intent);
                    } else if ("cliente".equals(role)) {
                        Intent intent = new Intent(this, UCMainActivity.class);
                        startActivity(intent);
                    }

                    finish();
                })
                .show();
    }

    @Override
    public void showLoginError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
