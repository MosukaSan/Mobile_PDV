package com.devlucaslima.cashierapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PDVMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pdv_main)

        val btnVoltar = findViewById<Button>(R.id.btnVoltarPDV)
        val btnCartao = findViewById<Button>(R.id.btnCartao)
        val btnDinheiro = findViewById<Button>(R.id.btnDinheiro)
        val btnAddProduto = findViewById<Button>(R.id.btnAddProduto)
        val txtTotalPago = findViewById<EditText>(R.id.txtTotalPago)
        val txtTotal = findViewById<TextView>(R.id.txtTotal)
        val txtTroco = findViewById<TextView>(R.id.txtTroco)

        btnVoltar.setOnClickListener{
            finish()
        }
        btnCartao.setOnClickListener{
            btnCartao.alpha = 1f
            btnDinheiro.alpha = 0.5f
        }
        btnDinheiro.setOnClickListener{
            btnDinheiro.alpha = 1f
            btnCartao.alpha = 0.5f
        }
    }
}