package com.example.intentscomunes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var btnAlarma   : Button
    private lateinit var btnContador : Button
    private lateinit var btnFotografia : Button
    private lateinit var btnTelefono : Button
    private lateinit var btnEmail : Button
    private lateinit var btnMapa  : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Preparar componentes
        this.prepararComponentes()
    }

    private fun prepararComponentes(){
        btnAlarma   = findViewById(R.id.btnAlarma)
        btnContador = findViewById(R.id.btnContador)
        btnFotografia = findViewById(R.id.btnFotografia)
        btnTelefono = findViewById(R.id.btnTelefono)
        btnEmail = findViewById(R.id.btnEmail)
        btnMapa  = findViewById(R.id.btnMapa)
        // Preparar acciones
        this.prepararAcciones()
    }

    private fun prepararAcciones(){
        val intent = Intent()
        // Preparar onClick Listeners para los botones
        // Alarma
        btnAlarma.setOnClickListener {
            intent.setClass(this, AlarmaActivity::class.java)
            startActivity(intent)
        }
        // Contador
        btnContador.setOnClickListener {
            intent.setClass(this, ContadorActivity::class.java)
            startActivity(intent)
        }
        // Fotografía
        btnFotografia.setOnClickListener {
            intent.setClass(this, FotografiaActivity::class.java)
            startActivity(intent)
        }
        // Email
        btnEmail.setOnClickListener {
            intent.setClass(this, EmailActivity::class.java)
            startActivity(intent)
        }
        // Mapa
        btnMapa.setOnClickListener {
            intent.setClass(this, MapaActivity::class.java)
            startActivity(intent)
        }
        // Teléfono
        btnTelefono.setOnClickListener {
            intent.setClass(this, TelefonoActivity::class.java)
            startActivity(intent)
        }
    }
}