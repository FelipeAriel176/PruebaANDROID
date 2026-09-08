package com.example.pruebaandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView tvBienvenida = findViewById(R.id.tvBienvenidaSandbox);
        Switch miSwitch = findViewById(R.id.switchIDD);
        CheckBox miCheck = findViewById(R.id.checkBox);
        ProgressBar barraProgreso = findViewById(R.id.progressBar);
        Button btnValidar = findViewById(R.id.button3);

        Intent intentRecibido = getIntent();
        String correo = intentRecibido.getStringExtra("CORREO_ENVIADO");

        if (correo != null) {
            tvBienvenida.setText("Usuario activo: " + correo);
        }

        btnValidar.setOnClickListener(v -> {
            int progreso = 0;
            if (miSwitch.isChecked()) {
                progreso += 50;
            }
            if (miCheck.isChecked()) {
                progreso += 50;
                miCheck.setError(null);
            } else {
                miCheck.setError("Debes confirmar tus datos");
            }
            barraProgreso.setProgress(progreso);
            if (progreso == 100) {
                Toast.makeText(MenuActivity.this, "¡Validación 100% completada!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}