package com.devlucaslima.cashierapp.pdvlista

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devlucaslima.cashierapp.R
import com.devlucaslima.cashierapp.pdvmain.PDVMainActivity
import com.devlucaslima.cashierapp.pdvmain.PDVMainArrayLista
import com.devlucaslima.cashierapp.pdvmain.PDVMainProduto

class PDVListaProdutos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pdv_adicionar)

        val listaProdutos = PDVArrayLista.pdvlistaProdutos

        val intentPDVMain = Intent(this, PDVMainActivity::class.java)
        val adapter = PDVProdutoAdapter(listaProdutos)
        val recyclerProdutos = findViewById<RecyclerView>(R.id.rvPdvAdicionar)
        val btnPdvAddVoltar = findViewById<Button>(R.id.btnPdvAddVoltar)

        btnPdvAddVoltar.setOnClickListener{
            finish()
        }

        adapter.setOnItemClickListener(object : PDVProdutoAdapter.OnItemClickListener {
            override fun onItemClick(produto: PDVProduto) {
                val precoString = produto.txtPdvPreco.replace(Regex("[^\\d.]"), "")
                val preco = precoString.toDoubleOrNull() ?: 0.0
                PDVMainActivity.price = PDVMainActivity.price + preco

                val pdvMainProduto = PDVMainProduto(produto.txtPdvNome, produto.txtPdvPreco, txtPdvQuantidade)

                PDVMainArrayLista.pdvMainListaProdutos.add(pdvMainProduto)
                startActivity(intentPDVMain)
                finish()
            }
        })

        recyclerProdutos.adapter = adapter
        recyclerProdutos.layoutManager = LinearLayoutManager(this)
        recyclerProdutos.setHasFixedSize(true)

    }
    companion object {
        var txtPdvQuantidade = ""
    }
}