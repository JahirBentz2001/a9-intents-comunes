package com.example.intentscomunes

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.core.content.FileProvider

class EmailActivity : AppCompatActivity() {

    private lateinit var etDest     : EditText
    private lateinit var etAsunto   : EditText
    private lateinit var bSendEmail : Button

    private val REQUEST_IMAGE_GET = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)
        // Preparar componentes
        this.prepararComponentes()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Regresar del intent de seleccionar imagen
        if(requestCode == REQUEST_IMAGE_GET && resultCode == Activity.RESULT_OK){
            val fullPhotoUri : Uri? = data?.data
            // Enviar el correo con la imagen adjunta
            if(fullPhotoUri != null){
                enviarEmail(fullPhotoUri)
            }
        }
    }

    private fun prepararComponentes(){
        etDest = findViewById(R.id.etDest)
        etAsunto = findViewById(R.id.etAsunto)
        bSendEmail = findViewById(R.id.bSendEmail)
        // Establecer evento para enviar email
        bSendEmail.setOnClickListener {
            this.enviarImagen()
        }
    }

    private fun enviarImagen(){
        // Iniciar acitivity para seleccionar imagen
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
            putExtra("return-data", true)
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        // Iniciar actividad con el resultado obtenido
        startActivityForResult(intent, REQUEST_IMAGE_GET)
    }

    private fun enviarEmail(photoUri : Uri){
        val destinatarios = arrayOf( etDest.text.toString().trim() )
        val asunto = etAsunto.text.toString().trim()

       //Log.d("URI", photoUri.toString())

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_EMAIL, destinatarios)
            putExtra(Intent.EXTRA_SUBJECT, asunto)
            putExtra(Intent.EXTRA_STREAM, photoUri)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}
