package com.devlucaslima.cashierapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PDVMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pdv_main)

        val btnVoltar = findViewById<Button>(R.id.btnVoltarPDV)

        btnVoltar.setOnClickListener{
            finish()
        }
    }
}