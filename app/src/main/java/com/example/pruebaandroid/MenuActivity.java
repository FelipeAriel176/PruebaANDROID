package com.example.pruebaandroid;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class MenuActivity extends AppCompatActivity {

    private TextToSpeech motorVoz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        TextView tvBienvenida = findViewById(R.id.tvBienvenidaSandbox);
        Switch miSwitch = findViewById(R.id.switchIDD);
        CheckBox miCheck = findViewById(R.id.checkBox);
        ProgressBar barraProgreso = findViewById(R.id.progressBar);
        Button btnValidar = findViewById(R.id.button3);
        RadioGroup grupoOpciones = findViewById(R.id.grupoOpciones);
        RatingBar ratingEstrellas = findViewById(R.id.ratingBar);
        EditText inputVoz = findViewById(R.id.inputVoz);
        Button btnHablar = findViewById(R.id.btnHablar);

        Intent intentRecibido = getIntent();
        String correo = intentRecibido.getStringExtra("Correo enviado");
        if (correo != null) {
            tvBienvenida.setText("Usuario activo: " + correo);
        }

        btnValidar.setOnClickListener(v -> {
            int progreso = 0;

            if (miSwitch.isChecked()) {
                progreso += 25;
            }

            if (miCheck.isChecked()) {
                progreso += 25;
                miCheck.setError(null);
            } else {
                miCheck.setError("Debe confirmar los datos");
            }

            if (grupoOpciones.getCheckedRadioButtonId() != -1) {
                progreso += 25;
            }

            if (ratingEstrellas.getRating() > 0) {
                progreso += 25;
            }

            barraProgreso.setProgress(progreso);

            if (progreso == 100) {
                Toast.makeText(MenuActivity.this, "Validación completada", Toast.LENGTH_SHORT).show();
            }
        });

        motorVoz = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                motorVoz.setLanguage(new Locale("es", "ES"));
            } else {
                Toast.makeText(this, "Error al iniciar", Toast.LENGTH_SHORT).show();
            }
        });

        btnHablar.setOnClickListener(v -> {
            String textoLeido = inputVoz.getText().toString();
            if (textoLeido.isEmpty()) {
                inputVoz.setError("Escribe algo");
            } else {
                motorVoz.speak(textoLeido, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });

    }
    @Override
    protected void onDestroy() {
        if (motorVoz != null) {
            motorVoz.stop();
            motorVoz.shutdown();
        }
        super.onDestroy();
    }
}