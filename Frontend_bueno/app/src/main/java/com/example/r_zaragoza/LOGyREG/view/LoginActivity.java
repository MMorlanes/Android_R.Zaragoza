package com.example.r_zaragoza.LOGyREG.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.r_zaragoza.MainActivity;
import com.example.r_zaragoza.R;
import com.example.r_zaragoza.LOGyREG.contracts.LoginContract;
import com.example.r_zaragoza.LOGyREG.presenter.LoginPresenter;
import com.example.r_zaragoza.UVdarAltaProd.view.UVMainActivity; // Nueva actividad para el vendedor

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
    public void showLoginSuccess(String message) {
        Log.e("", "llrgo");
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        // Simulamos que el rol viene del servidor (esto debe ser una respuesta real)
        String userRole = "vendedor"; // Este valor debe ser recuperado del servidor

        Log.d("LoginActivity", "Rol del usuario: " + userRole);  // Para depuración

        if (userRole.equals("vendedor")) {
            Log.d("LoginActivity", "Redirigiendo a UVMainActivity...");  // Depuración
            Intent intent = new Intent(this, UVMainActivity.class);
            startActivity(intent);
            finish(); // Finalizamos la actividad de login para que no regrese
        } else {
            Log.d("LoginActivity", "Redirigiendo a MainActivity...");  // Depuración
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish(); // Finalizamos la actividad de login para que no regrese
        }
    }

    @Override
    public void showLoginError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
