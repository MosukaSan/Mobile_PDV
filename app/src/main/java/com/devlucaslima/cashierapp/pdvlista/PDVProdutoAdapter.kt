package com.devlucaslima.cashierapp.pdvlista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devlucaslima.cashierapp.R
import com.devlucaslima.cashierapp.listaprodutos.ArrayLista
import com.devlucaslima.cashierapp.pdvmain.PDVMainActivity


class PDVProdutoAdapter (private val listProduto: ArrayList<PDVProduto>):
    RecyclerView.Adapter<PDVProdutoAdapter.PDVProdutoViewHolder>(){

    // Define uma interface para lidar com cliques nos botões
    interface OnItemClickListener {
        fun onItemClick(produto: PDVProduto)
    }

    // Variável para armazenar o listener
    private var listener: OnItemClickListener? = null

    // Função para definir o listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    inner class PDVProdutoViewHolder(view: View): RecyclerView.ViewHolder(view){

        val txtPdvNome = view.findViewById<TextView>(R.id.txtPdvNome)
        val txtPdvID = view.findViewById<TextView>(R.id.txtPdvID)
        val txtPdvPreco = view.findViewById<TextView>(R.id.txtPdvPreco)
        val btnPdvAdd = view.findViewById<Button>(R.id.btnPdvAdd)
        val txtPdvQuantidade = view.findViewById<EditText>(R.id.txtPdvQuantidade)

        init {
            // Adiciona o OnClickListener ao botão

            btnPdvAdd.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val quantidadeStr = txtPdvQuantidade.text.toString()
                    val quantidade: Int = try {
                        quantidadeStr.toInt()
                    } catch (e: NumberFormatException) {
                        1
                    }
                    PDVMainActivity.quantity += quantidade
                    PDVListaProdutos.txtPdvQuantidade = "Quantidade: $quantidadeStr"

                    val listaPrincipalQuantidade = ArrayLista.listaProdutos[position]
                    var quantidadeString = listaPrincipalQuantidade.txtQuantidade.replace(Regex("[^\\d.]"), "")
                    var quantidadePrincipal = quantidadeString.toInt() - quantidade
                    listaPrincipalQuantidade.txtQuantidade = "Quantidade: $quantidadePrincipal"

                    listener?.onItemClick(listProduto[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PDVProdutoViewHolder {
        val layoutAdapter = LayoutInflater.from(parent.context).inflate(R.layout.pdv_produto,parent, false)

        return PDVProdutoViewHolder(layoutAdapter)
    }

    override fun getItemCount(): Int {
        return listProduto.size
    }

    override fun onBindViewHolder(holder: PDVProdutoViewHolder, position: Int) {
        val produto = listProduto[position]

        holder.txtPdvNome.text = produto.txtPdvNome
        holder.txtPdvID.text = produto.txtPdvID
        holder.txtPdvPreco.text = produto.txtPdvPreco
    }
}