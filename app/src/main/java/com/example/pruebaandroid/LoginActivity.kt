package com.example.pruebaandroid

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val inputUsuarioLogin = findViewById<EditText>(R.id.editTextText2)
        val inputPassLogin = findViewById<EditText>(R.id.editTextTextPassword)
        val botonIngresar = findViewById<Button>(R.id.btnIngresar)
        val botonRegistro = findViewById<Button>(R.id.btnCrear)

        botonRegistro.setOnClickListener {
            val intentRegistro = Intent(this, RegistroActivity::class.java)
            startActivity(intentRegistro)
        }

        botonIngresar.setOnClickListener {
            val preferencias = getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE)
            val usuarioGuardado = preferencias.getString("Usuario", "")
            val passGuardada = preferencias.getString("Contrasena", "")
            val usuarioEscrito = inputUsuarioLogin.text.toString()
            val passEscrita = inputPassLogin.text.toString()
            if (usuarioEscrito == usuarioGuardado && passEscrita == passGuardada) {
                val intentBienvenida = Intent(this, Bienvenida::class.java)
                startActivity(intentBienvenida)

            } else {
                Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}