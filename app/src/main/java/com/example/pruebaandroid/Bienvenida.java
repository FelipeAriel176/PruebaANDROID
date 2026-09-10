package com.example.pruebaandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Bienvenida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bienvenida);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnMenu = findViewById(R.id.button2);
        Button btnRegresar = findViewById(R.id.button);

        btnMenu.setOnClickListener(v -> {
            Intent intentMenu = new Intent(Bienvenida.this, MenuActivity.class);
            startActivity(intentMenu);
        });

        btnRegresar.setOnClickListener(v -> {
            Intent intentLogin = new Intent(Bienvenida.this, LoginActivity.class);
            startActivity(intentLogin);
            finish();
        });
    }
}