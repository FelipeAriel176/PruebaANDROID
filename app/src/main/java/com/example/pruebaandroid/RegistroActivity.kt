package com.example.pruebaandroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
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
        val campoApellido = findViewById<EditText>(R.id.apellido1)
        val campoDireccion = findViewById<EditText>(R.id.editTextText4)
        val campoRUT = findViewById<EditText>(R.id.editTextText5)
        val campoEdad = findViewById<EditText>(R.id.editTextNumber)
        val campoTelefono = findViewById<EditText>(R.id.editTextPhone)
        val campoCorreo = findViewById<EditText>(R.id.correo)
        val campoPass = findViewById<EditText>(R.id.editTextTextPassword2)
        val btnValidar = findViewById<Button>(R.id.btnCrearr)
        val grupoGenero = findViewById<RadioGroup>(R.id.generoID)

        btnValidar.setOnClickListener {
            if (campoNombre.text.toString().isEmpty() || campoCorreo.text.toString().isEmpty() || campoPass.text.toString().isEmpty()) {
                Toast.makeText(this, "datos vacíos", Toast.LENGTH_SHORT).show()

            } else {
            val preferencias = getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE)
            val editor = preferencias.edit()
            val nombreIngresado = campoNombre.text.toString()
            editor.putString("Nombre", nombreIngresado)
            editor.putString("Usuario", campoCorreo.text.toString())
            editor.putString("Contrasena", campoPass.text.toString())
            editor.putString("Apellido", campoApellido.text.toString())
            editor.putString("Dirección", campoDireccion.text.toString())
            editor.putString("Rut", campoRUT.text.toString())
            editor.putString("Edad", campoEdad.text.toString())
            editor.putString("Telefono", campoTelefono.text.toString())

            val idSeleccionado = grupoGenero.checkedRadioButtonId
            if (idSeleccionado != -1) {
                val radioSeleccionado = findViewById<RadioButton>(idSeleccionado)
                editor.putString("Genero", radioSeleccionado.text.toString())
            } else {
                editor.putString("Genero", "No seleccionado")
            }
            editor.apply()
            val intentVolver = Intent(this, LoginActivity::class.java)
            startActivity(intentVolver)
            }
        }
    }
}