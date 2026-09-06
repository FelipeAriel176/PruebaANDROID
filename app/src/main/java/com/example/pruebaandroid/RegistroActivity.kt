package com.example.pruebaandroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import androidx.core.view.WindowInsetsCompat
class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val campoNombre = findViewById<EditText>(R.id.inputNombre1)
        val btnValidar = findViewById<Button>(R.id.btnCrearr)

        btnValidar.setOnClickListener {

            val preferencias = getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE)
            val editor = preferencias.edit()
            val nombreIngresado = campoNombre.text.toString()
            editor.putString("Nombre", nombreIngresado)
            editor.apply()
            val intentVolver = Intent(this, LoginActivity::class.java)
            startActivity(intentVolver)
        }
    }
}