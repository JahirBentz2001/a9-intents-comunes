package com.example.intentscomunes

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class EmailActivity : AppCompatActivity() {

    private lateinit var etDest     : EditText
    private lateinit var etAsunto   : EditText
    private lateinit var bSendEmail : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)
        // Preparar componentes
        this.prepararComponentes()
    }

    private fun prepararComponentes(){
        etDest = findViewById(R.id.etDest)
        etAsunto = findViewById(R.id.etAsunto)
        bSendEmail = findViewById(R.id.bSendEmail)
        // Establecer evento para enviar email
        bSendEmail.setOnClickListener {
            this.enviarEmail()
        }
    }

    private fun enviarEmail(){
        // Conseguir elementos
        // Los destinatarios deben estar en un arreglo
        val destinatarios = arrayOf( etDest.text.toString().trim() )
        val asunto = etAsunto.text.toString().trim()

        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, destinatarios)
            putExtra(Intent.EXTRA_SUBJECT, asunto)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}