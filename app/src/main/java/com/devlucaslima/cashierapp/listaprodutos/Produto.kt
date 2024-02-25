package com.devlucaslima.cashierapp.listaprodutos

import java.io.Serializable

class Produto (val txtNome: String,
               val txtID: String,
               var txtQuantidade: String,
               val txtPrecoDeFabrica: String,
               val txtNovoPreco: String,
               val txtLucro: String,
               val txtMargem: String) : Serializable{
}