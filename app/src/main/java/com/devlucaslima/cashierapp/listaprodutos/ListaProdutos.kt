package com.devlucaslima.cashierapp.listaprodutos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devlucaslima.cashierapp.R

class ListaProdutos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista_produtos)


        val intentListaAdiconarActivity = Intent(this, ListaAdicionar::class.java)
        val btnVoltar = findViewById<Button>(R.id.btnVoltarLista)
        val btnAdicionarProdutoLista = findViewById<Button>(R.id.btnAdicionarProdutoLista)

        btnVoltar.setOnClickListener{
            finish()
        }
        btnAdicionarProdutoLista.setOnClickListener{
            startActivity(intentListaAdiconarActivity)
            finish()
        }
        val listaProdutos = ArrayLista.listaProdutos

        val adapter = ProdutoAdapter(listaProdutos)
        val recyclerProdutos = findViewById<RecyclerView>(R.id.rvListaProdutos)

        recyclerProdutos.adapter = adapter
        recyclerProdutos.layoutManager = LinearLayoutManager(this)
        recyclerProdutos.setHasFixedSize(true)
    }
}