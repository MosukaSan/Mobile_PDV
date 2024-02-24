package com.devlucaslima.cashierapp.pdvlista

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devlucaslima.cashierapp.R

class PDVListaProdutos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pdv_adicionar)

        val listaProdutos = PDVArrayLista.pdvlistaProdutos

        val adapter = PDVProdutoAdapter(listaProdutos)
        val recyclerProdutos = findViewById<RecyclerView>(R.id.rvPdvAdicionar)
        val btnPdvAddVoltar = findViewById<Button>(R.id.btnPdvAddVoltar)

        btnPdvAddVoltar.setOnClickListener{
            finish()
        }

        adapter.setOnItemClickListener(object : PDVProdutoAdapter.OnItemClickListener {
            override fun onItemClick(produto: PDVProduto) {
            }
        })

        recyclerProdutos.adapter = adapter
        recyclerProdutos.layoutManager = LinearLayoutManager(this)
        recyclerProdutos.setHasFixedSize(true)
    }
}