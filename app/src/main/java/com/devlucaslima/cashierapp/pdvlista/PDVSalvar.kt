package com.devlucaslima.cashierapp.pdvlista

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PDVSalvar {
    companion object {
        private const val PREF_NAME = "PdvProdutoPrefs"
        private const val KEY_PRODUTOS = "PdvlistaProdutos"

        fun salvarLista(context: Context, lista: ArrayList<PDVProduto>) {
            val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            val gson = Gson()
            val json = gson.toJson(lista)
            editor.putString(KEY_PRODUTOS, json)
            editor.apply()
        }

        fun carregarLista(context: Context): ArrayList<PDVProduto> {
            val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val gson = Gson()
            val json = prefs.getString(KEY_PRODUTOS, null)
            val type = object : TypeToken<ArrayList<PDVProduto>>() {}.type
            return gson.fromJson(json, type) ?: ArrayList()
        }
    }
}