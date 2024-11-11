package com.example.r_zaragoza.LOGyREG.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.r_zaragoza.MainActivity;
import com.example.r_zaragoza.R;
import com.example.r_zaragoza.LOGyREG.contracts.RegisterContract;
import com.example.r_zaragoza.LOGyREG.presenter.RegisterPresenter;
import com.example.r_zaragoza.UVdarAltaProd.view.UVMainActivity; // Actividad para el vendedor

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {
    private EditText etUsername, etEmail, etPassword;
    private Spinner spinnerRol;
    private Button btnRegister;
    private RegisterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        spinnerRol = findViewById(R.id.spinnerRol);
        btnRegister = findViewById(R.id.btnRegister);

        presenter = new RegisterPresenter(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onRegisterClicked();
            }
        });
    }

    @Override
    public String getUsername() {
        return etUsername.getText().toString().trim();
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
    public String getRol() {
        return spinnerRol.getSelectedItem().toString();  // Obtener la selección del Spinner
    }

    @Override
    public void showRegisterSuccess(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Registro Exitoso")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();

                    // Obtén el rol seleccionado en el spinner al momento del registro
                    String userRole = getRol();
                    Log.d("RegisterActivity", "Rol del usuario registrado: " + userRole); // Depuración

                    if (userRole.equals("vendedor")) {
                        Log.d("RegisterActivity", "Redirigiendo a UVMainActivity...");  // Depuración
                        Intent intent = new Intent(RegisterActivity.this, UVMainActivity.class);
                        startActivity(intent);
                        finish(); // Finalizamos la actividad de registro
                    } else {
                        Log.d("RegisterActivity", "Redirigiendo a MainActivity...");  // Depuración
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Finalizamos la actividad de registro
                    }
                })
                .show();
    }

    @Override
    public void showRegisterError(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error en el Registro")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
