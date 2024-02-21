package com.devlucaslima.cashierapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProdutoAdapter (private val listProduto: ArrayList<Produto>):
    RecyclerView.Adapter<ProdutoAdapter.PostagemViewHolder>(){
    class PostagemViewHolder(view: View): RecyclerView.ViewHolder(view){

        val txtNome = view.findViewById<TextView>(R.id.txtNome)
        val txtID = view.findViewById<TextView>(R.id.txtID)
        val txtQuantidade = view.findViewById<TextView>(R.id.txtQuantidade)
        val txtPrecoDeFabrica = view.findViewById<TextView>(R.id.txtPrecoDeFabrica)
        val txtNovoPreco = view.findViewById<TextView>(R.id.txtNovoPreco)
        val txtLucro = view.findViewById<TextView>(R.id.txtLucro)
        val txtMargem = view.findViewById<TextView>(R.id.txtMargem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostagemViewHolder {
        val layoutAdapter = LayoutInflater.from(parent.context).inflate(R.layout.produto,parent, false)

        return PostagemViewHolder(layoutAdapter)
    }

    override fun getItemCount(): Int {
        return listProduto.size
    }

    override fun onBindViewHolder(holder: PostagemViewHolder, position: Int) {
        val produto = listProduto[position]

        holder.txtNome.text = produto.txtNome
        holder.txtID.text = produto.txtID
        holder.txtQuantidade.text = produto.txtQuantidade
        holder.txtPrecoDeFabrica.text = produto.txtPrecoDeFabrica
        holder.txtNovoPreco.text = produto.txtNovoPreco
        holder.txtLucro.text = produto.txtLucro
        holder.txtMargem.text = produto.txtMargem
    }
}