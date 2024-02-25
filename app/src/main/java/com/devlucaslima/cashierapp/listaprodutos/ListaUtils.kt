package com.devlucaslima.cashierapp.listaprodutos

import android.content.Context

object ListaUtils {
    fun carregarListaProdutos(context: Context): ArrayList<Produto> {
        return Salvar.carregarLista(context)
    }
}