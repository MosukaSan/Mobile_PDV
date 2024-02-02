package com.devlucaslima.cashierapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class ListaAdicionarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista_adicionar)

        //Variáveis
        val intentListaProdutos = Intent(this, ListaProdutosActivity::class.java)
        val btnCalcularLucro = findViewById<Button>(R.id.btnCalcularLucro)
        val txtMargemLucro = findViewById<EditText>(R.id.txtMargemLucro)
        val txtAddMargem = findViewById<TextView>(R.id.txtAddMargem)
        val btnConfirmarCalLucro = findViewById<Button>(R.id.btnConfirmarCalLucro)
        val btnAddVoltar = findViewById<Button>(R.id.btnAddVoltar)
        val btnAddFinalizar = findViewById<Button>(R.id.btnAddFinalizar)
        val txtAddProduto = findViewById<EditText>(R.id.txtAddProduto)
        val txtAddQuantidade = findViewById<EditText>(R.id.txtAddQuantidade)
        val txtAddFabrica = findViewById<EditText>(R.id.txtAddFabrica)
        val txtAddNovoPreco = findViewById<EditText>(R.id.txtAddNovoPreco)
        val txtAddLucro = findViewById<TextView>(R.id.txtAddLucro)
        var id = 1

        //Botões essenciais
        btnCalcularLucro.setOnClickListener{
            txtMargemLucro.visibility = View.VISIBLE
            btnConfirmarCalLucro.visibility = View.VISIBLE
        }
        btnAddVoltar.setOnClickListener{
            finish()
        }
        btnConfirmarCalLucro.setOnClickListener{
            val fabricaDouble = txtAddFabrica.text.toString().toDouble()
            val margemLucroDouble = txtMargemLucro.text.toString().toDouble()
            val lucro = fabricaDouble * margemLucroDouble / 100
            val novoPreco = fabricaDouble + lucro

            val lucroString = lucro.toString().toFloat().toString()
            val novoPrecoString = novoPreco.toString().toFloat().toString()

            val editableNovoPreco: Editable = Editable.Factory.getInstance().newEditable(novoPrecoString)
            txtAddNovoPreco.text = editableNovoPreco

            txtAddLucro.text = "Lucro: R$$lucroString"
            txtAddMargem.text = "Margem: $margemLucroDouble%"
        }

        //Calcular Lucro automaticamente
        val novoPrecoString = txtAddNovoPreco.text.toString()

        txtAddNovoPreco.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val fabrica = txtAddFabrica.text.toString().toFloat()
                val novoPreco = txtAddNovoPreco.text.toString().toFloat()
                val subtracao = novoPreco - fabrica
                val resultado = 100 * subtracao / fabrica
                val resultadoFloat = resultado.toString().toFloat()

                txtAddLucro.text = "Lucro: R$ $subtracao"
                txtAddMargem.text = "Margem: $resultadoFloat%"
            }
        }

        //Salvando na array
        btnAddFinalizar.setOnClickListener{
            val txtAddProdutoString = txtAddProduto.text.toString()
            val txtAddQuantidadeString = txtAddQuantidade.text.toString()
            val txtAddFabricaString = txtAddFabrica.text.toString()
            val txtAddNovoPrecoString = txtAddNovoPreco.text.toString()
            val txtAddLucroString = txtAddLucro.text.toString()
            val idString = id.toString()
            id++

            val novoProduto = Produto("Produto: $txtAddProdutoString", "id: $idString", "Quantidade: $txtAddQuantidadeString",
                "Preço de fábrica: $txtAddFabricaString", "Novo preço: $txtAddNovoPrecoString", "$txtAddLucroString")

            ArrayLista.listaProdutos.add(novoProduto)
            startActivity(intentListaProdutos)
            finish()
        }
    }
}