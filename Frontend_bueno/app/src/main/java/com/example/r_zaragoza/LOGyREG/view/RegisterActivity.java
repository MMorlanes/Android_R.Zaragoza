package com.example.r_zaragoza.LOGyREG.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.r_zaragoza.R;
import com.example.r_zaragoza.LOGyREG.contracts.RegisterContract;
import com.example.r_zaragoza.LOGyREG.presenter.RegisterPresenter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {
    private EditText etUsername, etEmail, etPassword;
    private Spinner spinnerRol;  // Cambiado de EditText a Spinner
    private Button btnRegister;
    private RegisterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicializar vistas
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        spinnerRol = findViewById(R.id.spinnerRol);  // Cambiado a Spinner
        btnRegister = findViewById(R.id.btnRegister);

        // Inicializar presenter
        presenter = new RegisterPresenter(this);

        // Configurar evento del botón
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onRegisterClicked();
            }
        });
    }

    // Implementación de los métodos de la interfaz View
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
        // Mostrar un diálogo de éxito
        new AlertDialog.Builder(this)
                .setTitle("Registro Exitoso")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                    // Opcional: Puedes cerrar esta actividad y regresar a la pantalla de login
                    finish();
                })
                .show();
    }

    @Override
    public void showRegisterError(String message) {
        // Mostrar un diálogo de error
        new AlertDialog.Builder(this)
                .setTitle("Error en el Registro")
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
