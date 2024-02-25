package com.devlucaslima.cashierapp.pdvmain

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.alpha
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devlucaslima.cashierapp.R
import com.devlucaslima.cashierapp.listaprodutos.ArrayLista
import com.devlucaslima.cashierapp.listaprodutos.Salvar
import com.devlucaslima.cashierapp.pdvlista.PDVArrayLista
import com.devlucaslima.cashierapp.pdvlista.PDVListaProdutos
import com.devlucaslima.cashierapp.pdvlista.PDVSalvar

class PDVMainActivity : AppCompatActivity() {

    fun salvarListaProdutos(context: Context) {
        val listaProdutos = ArrayLista.listaProdutos // Obtenha a lista de produtos da classe ArrayLista
        Salvar.salvarLista(context, listaProdutos) // Salve a lista de produtos usando a classe ArrayLista
    }

    fun pdvSalvarListaProdutos(context: Context) {
        val listaProdutos = PDVArrayLista.pdvlistaProdutos // Obtenha a lista de produtos da classe ArrayLista
        PDVSalvar.salvarLista(context, listaProdutos) // Salve a lista de produtos usando a classe ArrayLista
    }

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
        val btnFinalizar = findViewById<Button>(R.id.btnFinalizar)
        val txtTotalPago = findViewById<EditText>(R.id.txtTotalPago)
        val txtTotal = findViewById<TextView>(R.id.txtTotal)
        val txtTroco = findViewById<TextView>(R.id.txtTroco)
        val txtWarnPagamento = findViewById<TextView>(R.id.txtWarnPagamento)
        val txtWarnTotal = findViewById<TextView>(R.id.txtWarnTotal)
        val multiplicacao = quantity * price
        var totalPago = 0.0
        var dinheiro = false
        saveValue += multiplicacao
        var saveValueArredondado = "%.2f".format(saveValue).toDouble()

        recyclerProdutos.adapter = adapter
        recyclerProdutos.layoutManager = LinearLayoutManager(this)
        recyclerProdutos.setHasFixedSize(true)

        btnVoltar.setOnClickListener{
            finish()
        }
        btnCartao.setOnClickListener{
            txtWarnPagamento.visibility = View.INVISIBLE
            btnCartao.alpha = 1f
            btnDinheiro.alpha = 0.5f
            dinheiro = false
        }
        btnDinheiro.setOnClickListener{
            txtWarnPagamento.visibility = View.INVISIBLE
            btnDinheiro.alpha = 1f
            btnCartao.alpha = 0.5f
            dinheiro = true
        }
        btnAddProduto.setOnClickListener{
            startActivity(intentPDVAdicionar)
            finish()
        }

        btnFinalizar.setOnClickListener{
            if (totalPago > saveValueArredondado){
                quantity = 0
                price = 0.0
                saveValue = 0.0
                PDVMainArrayLista.pdvMainListaProdutos.clear()
                salvarListaProdutos(this)
                pdvSalvarListaProdutos(this)
                finish()
            } else if (totalPago < saveValueArredondado){
                PDVMainArrayLista.pdvMainListaProdutos.clear()
                txtWarnTotal.visibility = View.VISIBLE
            }
        }

        txtTotal.text = "Total: R$$saveValueArredondado"
        quantity = 0
        price = 0.0


        txtTotalPago.setOnEditorActionListener { _, actionId, _ ->
            // Verifica se a ação é a ação do teclado "Confere" (ou outra ação que você deseja)
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Remove o foco do EditText
                txtTotalPago.clearFocus()

                // Esconde o teclado
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(txtTotalPago.windowToken, 0)
                true // Retorna true para indicar que o evento foi tratado
            } else {
                false // Retorna false para indicar que o evento não foi tratado aqui e será tratado de outra forma (se houver)
            }
        }

        txtTotalPago.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (dinheiro){
                    val totalPagoString = txtTotalPago.text.toString()
                    if (totalPagoString.isNotEmpty()) {
                        totalPago = totalPagoString.toDouble()
                        if (dinheiro && totalPago >= saveValueArredondado) {
                            txtWarnTotal.visibility = View.INVISIBLE
                            val subtracao = totalPago - saveValueArredondado
                            val subtracaoArredondada = "%.2f".format(subtracao).toDouble()

                            txtTroco.text = "Troco: R$$subtracaoArredondada"
                        } else {
                            txtWarnTotal.visibility = View.VISIBLE
                        }
                    }
                } else {
                    txtWarnPagamento.visibility = View.VISIBLE
                }
            }
        }
    }
    override fun onBackPressed() {
        var txtTotalPago = findViewById<EditText>(R.id.txtTotalPago)

        txtTotalPago.setOnFocusChangeListener { _, hasFocus ->
            txtTotalPago.clearFocus()
        }

        if (isTecladoVisivel()) {
            // Se o teclado estiver visível, apenas oculte a visão associada ao teclado
            ocultarTeclado()
        } else {
            // Se o teclado não estiver visível, permita o comportamento padrão de retorno
            super.onBackPressed()
        }
    }

    private fun isTecladoVisivel(): Boolean {
        // Verifique se o teclado está visível
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.isAcceptingText
    }

    private fun ocultarTeclado() {
        // Oculte o teclado
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusView = currentFocus
        if (currentFocusView != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocusView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}