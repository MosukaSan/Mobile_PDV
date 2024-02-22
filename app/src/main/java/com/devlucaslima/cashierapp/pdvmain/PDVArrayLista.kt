package com.devlucaslima.cashierapp.pdvmain

import com.devlucaslima.cashierapp.listaprodutos.Produto

class PDVArrayLista {
    companion object {
        val pdvlistaProdutos = arrayListOf(
            PDVProduto("Nome: Exemplo", "id: 0", "Preço: R$0.00")
        )
    }
}