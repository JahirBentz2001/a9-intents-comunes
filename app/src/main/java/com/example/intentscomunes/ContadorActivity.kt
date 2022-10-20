package com.example.intentscomunes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import android.widget.EditText

class ContadorActivity : AppCompatActivity() {

        private lateinit var etCounter : EditText
        private lateinit var etSeconds : EditText
        private lateinit var bSetCounter : Button

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_contador)

            idBotones()
        }

        private fun idBotones() {
            etCounter = findViewById(R.id.etCounter)
            etSeconds = findViewById(R.id.etSeconds)
            bSetCounter = findViewById(R.id.bSetCounter)

            accionesBotones()
        }

        private fun accionesBotones() {
            bSetCounter.setOnClickListener{
                setCounter()
            }
        }

        private fun setCounter() {
            val nombreContador : String = etCounter.text.toString().trim()
            val segundos : Int = etSeconds.text.toString().toInt()

            val intent = Intent(AlarmClock.ACTION_SET_TIMER).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, nombreContador)
                putExtra(AlarmClock.EXTRA_LENGTH, segundos)
                putExtra(AlarmClock.EXTRA_SKIP_UI, true)
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
}