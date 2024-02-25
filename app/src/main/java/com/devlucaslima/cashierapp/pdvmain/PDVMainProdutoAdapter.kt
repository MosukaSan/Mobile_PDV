package com.devlucaslima.cashierapp.pdvmain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devlucaslima.cashierapp.R
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
                    listProduto.removeAt(position)
                    notifyItemRemoved(position)
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
