package com.example.intentscomunes

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MapaActivity : AppCompatActivity() {

    private lateinit var etAddress : EditText
    private lateinit var bAddress : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)
        // Preparar componentes
        this.prepararComponentes()
    }

    private fun prepararComponentes(){
        // Preparar componentes
        etAddress = findViewById(R.id.etAddress)
        bAddress  = findViewById(R.id.bAddress)
        // Preparar mapa
        bAddress.setOnClickListener {
            this.buscarEnMapa()
        }
    }

    private fun buscarEnMapa(){
        val direccion = etAddress.text.toString().trim()
        // Iniciar intent de dirección
        val intent = Intent(Intent.ACTION_VIEW).apply {
            // Las coordenadas especificadas pertenecen a la UCC
            // La dirección se codifica para que ingrese cómo un query para la app de mapas
            //data = Uri.parse("geo:19.1550723,-96.1344649?q=" + Uri.encode(direccion))
            data = Uri.parse("geo:?q=" + Uri.encode(direccion))
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}