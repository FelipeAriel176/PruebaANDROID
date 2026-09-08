package com.example.pruebaandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText inputUsuario = findViewById(R.id.editTextText2);
        EditText inputPass = findViewById(R.id.editTextTextPassword);
        Button btnIngresar = findViewById(R.id.btnIngresar);
        Button btnRegistro = findViewById(R.id.btnCrear);

        btnRegistro.setOnClickListener(v -> {
            Intent intentRegistro = new Intent(LoginActivity.this, RegistroActivity.class);
            startActivity(intentRegistro);
        });

        btnIngresar.setOnClickListener(v -> {
            String usuarioEscrito = inputUsuario.getText().toString().trim();
            String passEscrita = inputPass.getText().toString().trim();

            if (usuarioEscrito.isEmpty() || passEscrita.isEmpty()) {
                if (usuarioEscrito.isEmpty()) inputUsuario.setError("Ingrese su correo");
                if (passEscrita.isEmpty()) inputPass.setError("Ingrese su contraseña");
            } else {
                SharedPreferences preferencias = getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE);
                String usuarioGuardado = preferencias.getString("Usuario", "");
                String passGuardada = preferencias.getString("Contrasena", "");

                if (usuarioEscrito.equals(usuarioGuardado) && passEscrita.equals(passGuardada)) {
                    Intent intent = new Intent(LoginActivity.this, Bienvenida.class);
                    intent.putExtra("Correo enviado", usuarioEscrito);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Datos erróneos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}