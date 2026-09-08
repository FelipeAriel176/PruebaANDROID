package com.example.pruebaandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        EditText campoNombre = findViewById(R.id.inputNombre1);
        EditText campoCorreo = findViewById(R.id.correo);
        EditText campoPass = findViewById(R.id.editTextTextPassword2);
        Button btnValidar = findViewById(R.id.btnCrearr);

        btnValidar.setOnClickListener(v -> {
            String nombre = campoNombre.getText().toString().trim();
            String correo = campoCorreo.getText().toString().trim();
            String pass = campoPass.getText().toString().trim();

            if (nombre.isEmpty() || correo.isEmpty() || pass.isEmpty()) {
                Toast.makeText(RegistroActivity.this, "Llene los campos obligatorios", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences preferencias = getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencias.edit();

                editor.putString("Nombre", nombre);
                editor.putString("Usuario", correo);
                editor.putString("Contrasena", pass);
                editor.apply();
                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}