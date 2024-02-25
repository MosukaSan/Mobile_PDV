package com.devlucaslima.cashierapp.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.devlucaslima.cashierapp.MainActivity
import com.devlucaslima.cashierapp.R

class SettingsActivity : AppCompatActivity() {

    fun salvarString(context: Context, chave: String, valor: String) {
        // Obtendo uma referência ao SharedPreferences
        val sharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)

        // Obtendo um objeto Editor para realizar operações de edição
        val editor = sharedPreferences.edit()

        // Salvando a string
        editor.putString(chave, valor)

        // Aplicando as mudanças
        editor.apply()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        val intentMain = Intent(this, MainActivity::class.java)
        val btnVoltarSettings = findViewById<Button>(R.id.btnVoltarSettings)
        val btnSalvarSettings = findViewById<Button>(R.id.btnSalvarSettings)
        val txtMudarTitulo = findViewById<EditText>(R.id.txtMudarTituIo)

        btnVoltarSettings.setOnClickListener{
            startActivity(intentMain)
            finish()
        }
        btnSalvarSettings.setOnClickListener{
            val txtMudarTituloString = txtMudarTitulo.text.toString()
            GlobalSettings.titulo = txtMudarTituloString
            salvarString(this, "menuTitulo", GlobalSettings.titulo)
            startActivity(intentMain)
            finish()
        }
    }
}