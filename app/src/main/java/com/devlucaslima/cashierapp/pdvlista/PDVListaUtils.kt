package com.devlucaslima.cashierapp.pdvlista

import android.content.Context

object PDVListaUtils {
    fun carregarListaProdutos(context: Context): ArrayList<PDVProduto> {
            return PDVSalvar.carregarLista(context)
    }
}
