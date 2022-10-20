package com.example.intentscomunes

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup

class TelefonoActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var button: Button

    private val telefonos = arrayListOf(
        "229 688 2909", // Domino's Nuevo Veracruz
        "55 1515 3737", // Pizza Hut Coyol
        "229 922 2800", // Benedetti's Boca del Río
        "229 931 0015", // Angelo Av. Cuauhtémoc
        "229 935 3666", // Pepperrones Pizza en Reforma
        "229 213 9573" // The Lucky Pizza en Reforma
    )

    private val checkboxes = arrayListOf(
        R.id.dominos,
        R.id.hut,
        R.id.benedettis,
        R.id.angelo,
        R.id.pepperrones,
        R.id.lucky
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telefono)
        // Preparar componentes
        this.prepararComponentes()
    }

    private fun prepararComponentes(){
        radioGroup = findViewById(R.id.radioGroup)
        button = findViewById(R.id.button)
        // Preparar llamada
        button.setOnClickListener {
            this.llamarLocal()
        }
    }

    private fun llamarLocal() {
        // Obtener local seleccionad
        val local = telefonos[checkboxes.indexOf(radioGroup.checkedRadioButtonId)]
        // Intent para abrir la app de teléfono
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$local")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }

    }
}