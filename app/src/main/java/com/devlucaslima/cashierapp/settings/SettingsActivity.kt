package com.devlucaslima.cashierapp.settings

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.devlucaslima.cashierapp.MainActivity
import com.devlucaslima.cashierapp.R

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        val btnVoltarSettings = findViewById<Button>(R.id.btnVoltarSettings)
        val btnSalvarSettings = findViewById<Button>(R.id.btnSalvarSettings)
        val txtMudarTitulo = findViewById<EditText>(R.id.txtMudarTituIo)

        btnVoltarSettings.setOnClickListener{
            finish()
        }
        btnSalvarSettings.setOnClickListener{
            val intentMain = Intent(this, MainActivity::class.java)
            val txtMudarTituloString = txtMudarTitulo.text.toString()
            GlobalSettings.titulo = txtMudarTituloString
            startActivity(intentMain)
            finish()
        }
    }
}