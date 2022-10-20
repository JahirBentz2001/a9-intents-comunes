package com.example.intentscomunes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner

class AlarmaActivity : AppCompatActivity() {

    private lateinit var etAlarmName : EditText
    private lateinit var sAlarmHour  : Spinner
    private lateinit var sAlarmMinutes : Spinner
    private lateinit var bSetAlarm : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarma)
        // Preparar componentes
        this.prepararComponentes()
    }

    private fun prepararComponentes(){
        etAlarmName = findViewById(R.id.etAlarmName)
        sAlarmHour  = findViewById(R.id.sAlarmHours)
        sAlarmMinutes = findViewById(R.id.sAlarmMinutes)
        bSetAlarm = findViewById(R.id.bSetAlarm)
        // Preparar spinner
        this.prepararSpinners()
        // Establecer alarma
        bSetAlarm.setOnClickListener {
            this.establecerAlarma()
        }
    }

    private fun prepararSpinners(){
        // Rellenar los spinners con las horas y minutos
        val horas   = ArrayList<Int>()
        val minutos = ArrayList<Int>()

        // Llenar las listas
        // Horas
        for(i in 1 .. 24){
            horas.add(i-1)
        }

        // Minutos
        for(i in 1 .. 60){
            minutos.add(i-1)
        }

        // Establecer ArrayAdapters
        val horaAdapter = ArrayAdapter<Int>(this, android.R.layout.simple_expandable_list_item_1, horas)
        val minAdapter  = ArrayAdapter<Int>(this, android.R.layout.simple_expandable_list_item_1, minutos)
        horaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        minAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Adjuntar a Spinners
        sAlarmHour.adapter = horaAdapter
        sAlarmMinutes.adapter = minAdapter
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun establecerAlarma(){
        // Conseguir valores
        val nombre_alarma = etAlarmName.text.toString().trim()
        val horas_alarma = sAlarmHour.selectedItem.toString().toInt()
        val mins_alarma  = sAlarmMinutes.selectedItem.toString().toInt()

        // Establecer intent para alarma
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, nombre_alarma)
            putExtra(AlarmClock.EXTRA_HOUR, horas_alarma)
            putExtra(AlarmClock.EXTRA_MINUTES, mins_alarma)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}