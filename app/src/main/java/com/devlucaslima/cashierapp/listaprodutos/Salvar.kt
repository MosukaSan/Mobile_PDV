package com.devlucaslima.cashierapp.listaprodutos

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Salvar {
    companion object {
        private const val PREF_NAME = "ProdutoPrefs"
        private const val KEY_PRODUTOS = "listaProdutos"

        fun salvarLista(context: Context, lista: ArrayList<Produto>) {
            val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            val gson = Gson()
            val json = gson.toJson(lista)
            editor.putString(KEY_PRODUTOS, json)
            editor.apply()
        }

        fun carregarLista(context: Context): ArrayList<Produto> {
            val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val gson = Gson()
            val json = prefs.getString(KEY_PRODUTOS, null)
            val type = object : TypeToken<ArrayList<Produto>>() {}.type
            return gson.fromJson(json, type) ?: ArrayList()
        }
    }
}