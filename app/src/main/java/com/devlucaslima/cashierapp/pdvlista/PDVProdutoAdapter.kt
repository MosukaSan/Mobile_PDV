package com.devlucaslima.cashierapp.pdvlista

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devlucaslima.cashierapp.R
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
                    listener?.onItemClick(listProduto[position])
                }
            }
            txtPdvQuantidade.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    // Não é necessário implementar este método
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Não é necessário implementar este método
                }

                override fun afterTextChanged(s: Editable?) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        // Obter a quantidade digitada pelo usuário como uma string
                        val quantidadeStr = s.toString()

                        // Converter a quantidade para um valor numérico
                        val quantidade: Int = try {
                            quantidadeStr.toInt()
                        } catch (e: NumberFormatException) {
                            // Caso a entrada do usuário não seja um número válido
                            1 // Defina um valor padrão ou trate o erro conforme necessário
                        }
                        PDVMainActivity.quantity = PDVMainActivity.quantity + quantidade
                        PDVListaProdutos.txtPdvQuantidade = quantidadeStr
                    }
                }
            })
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