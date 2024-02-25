package com.devlucaslima.cashierapp.pdvmain

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devlucaslima.cashierapp.R
import com.devlucaslima.cashierapp.listaprodutos.ArrayLista

class PDVMainProdutoAdapter (private val listProduto: ArrayList<PDVMainProduto>):
    RecyclerView.Adapter<PDVMainProdutoAdapter.PDVMainProdutoViewHolder>(){

    inner class PDVMainProdutoViewHolder(view: View): RecyclerView.ViewHolder(view){

        val txtMainPdvNome = view.findViewById<TextView>(R.id.txtPdvMainNome)
        val txtMainPdvPreco = view.findViewById<TextView>(R.id.txtPdvMainPreco)
        val txtMainPdvQuantidade = view.findViewById<TextView>(R.id.txtPdvMainQuantidade)
        val btnPdvMainRemover = view.findViewById<Button>(R.id.btnPdvMainRemover)

        init {
            btnPdvMainRemover.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Pegar o produto da lista na posição especificada
                    val produtoRemovido = listProduto[position]

                    // Extrair o preço do produto removido
                    val precoString = produtoRemovido.txtPdvMainPreco.replace(Regex("[^\\d.]"), "")
                    val precoRemovido = precoString.toDoubleOrNull() ?: 0.0

                    val quantidadeString = produtoRemovido.txtPdvMainQuantidade.replace(Regex("[^\\d.]"), "")
                    val quantidade = quantidadeString.toDoubleOrNull() ?: 0.0

                    PDVMainActivity.saveValue = PDVMainActivity.saveValue - precoRemovido*quantidade

                    // Remover o produto da lista
                    listProduto.removeAt(position)
                    notifyItemRemoved(position)

                    val context = itemView.context

                    val listaPrincipalQuantidade = ArrayLista.listaProdutos[position]
                    val quantidadeStringPrincipal = listaPrincipalQuantidade.txtQuantidade.replace(Regex("[^\\d.]"), "")
                    val quantidadePrincipal = quantidadeStringPrincipal.toInt() + quantidade.toInt()
                    listaPrincipalQuantidade.txtQuantidade = "Quantidade: $quantidadePrincipal"

                    // Crie um Intent para iniciar a nova atividade
                    val intent = Intent(context, PDVMainActivity::class.java)

                    // Finalize a atividade atual, se necessário
                    (context as? PDVMainActivity)?.finish()
                    // Inicie a nova atividade
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PDVMainProdutoViewHolder {
        val layoutAdapter = LayoutInflater.from(parent.context).inflate(R.layout.pdv_main_produto,parent, false)

        return PDVMainProdutoViewHolder(layoutAdapter)
    }

    override fun getItemCount(): Int {
        return listProduto.size
    }

    override fun onBindViewHolder(holder: PDVMainProdutoViewHolder, position: Int) {
        val produto = listProduto[position]

        holder.txtMainPdvNome.text = produto.txtPdvMainNome
        holder.txtMainPdvPreco.text = produto.txtPdvMainPreco
        holder.txtMainPdvQuantidade.text = produto.txtPdvMainQuantidade
    }
}
