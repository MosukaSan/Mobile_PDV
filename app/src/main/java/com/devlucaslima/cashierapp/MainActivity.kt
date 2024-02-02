package com.devlucaslima.cashierapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentPDVMain = Intent(this,PDVMainActivity::class.java)
        val intentListaProdutos = Intent(this, ListaProdutosActivity::class.java)
        val intentSettings = Intent(this, SettingsActivity::class.java)
        val btnPDV = findViewById<Button>(R.id.btnPDV)
        val btnListaProdutos = findViewById<Button>(R.id.btnListaProdutos)
        val btnSettings = findViewById<ImageButton>(R.id.btnSettings)
        val txtTitulo = findViewById<TextView>(R.id.txtTitulo)

        //Título
        txtTitulo.text = GlobalSettings.titulo

        btnPDV.setOnClickListener{
            startActivity(intentPDVMain)
        }
        btnListaProdutos.setOnClickListener{
            startActivity(intentListaProdutos)
        }
        btnSettings.setOnClickListener{
            startActivity(intentSettings)
            finish()
        }
    }
}