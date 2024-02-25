package com.devlucaslima.cashierapp.pdvmain

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devlucaslima.cashierapp.R
import com.devlucaslima.cashierapp.pdvlista.PDVArrayLista
import com.devlucaslima.cashierapp.pdvlista.PDVListaProdutos
import com.devlucaslima.cashierapp.pdvlista.PDVProduto
import com.devlucaslima.cashierapp.pdvlista.PDVProdutoAdapter

class PDVMainActivity : AppCompatActivity() {

    companion object{
        var quantity = 0
        var price = 0.0
        var saveValue = 0.0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pdv_main)

        val listaProdutos = PDVMainArrayLista.pdvMainListaProdutos
        val adapter = PDVMainProdutoAdapter(listaProdutos)
        val recyclerProdutos = findViewById<RecyclerView>(R.id.rvPdvMain)
        val intentPDVAdicionar = Intent(this, PDVListaProdutos::class.java)
        val btnVoltar = findViewById<Button>(R.id.btnVoltarPDV)
        val btnCartao = findViewById<Button>(R.id.btnCartao)
        val btnDinheiro = findViewById<Button>(R.id.btnDinheiro)
        val btnAddProduto = findViewById<Button>(R.id.btnAddProduto)
        val txtTotalPago = findViewById<EditText>(R.id.txtTotalPago)
        val txtTotal = findViewById<TextView>(R.id.txtTotal)
        val txtTroco = findViewById<TextView>(R.id.txtTroco)
        val multiplicacao = quantity * price

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
        btnAddProduto.setOnClickListener{
            startActivity(intentPDVAdicionar)
            finish()
        }

        saveValue += multiplicacao
        txtTotal.text = "Total: R$" + saveValue.toString()
        quantity = 0
        price = 0.0

        recyclerProdutos.adapter = adapter
        recyclerProdutos.layoutManager = LinearLayoutManager(this)
        recyclerProdutos.setHasFixedSize(true)
    }
}