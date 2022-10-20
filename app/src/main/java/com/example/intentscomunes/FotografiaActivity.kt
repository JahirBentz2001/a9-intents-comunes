package com.example.intentscomunes

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView

class FotografiaActivity : AppCompatActivity() {

    private lateinit var ivPhoto : ImageView
    private lateinit var bTakePhoto : Button

    // Código para ActivityResult
    private val REQUEST_IMAGE_CAPTURE : Int = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fotografia)
        // Preparar componentes
        this.prepararComponentes()
    }

    // RESULTADO DE TOMAR LA FOTO
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            val thumbnail: Bitmap? = data?.getParcelableExtra("data")
            // Establecer la foto capturada
            if(thumbnail != null){
                ivPhoto.setImageBitmap(thumbnail)
            }
        }
    }

    private fun prepararComponentes(){
        ivPhoto = findViewById(R.id.ivPhoto)
        bTakePhoto = findViewById(R.id.bTakePhoto)
        // Tomar foto
        bTakePhoto.setOnClickListener {
            this.tomarFoto()
        }
    }

    private fun tomarFoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        }
    }
}