package com.devlucaslima.cashierapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.devlucaslima.cashierapp.listaprodutos.ArrayLista
import com.devlucaslima.cashierapp.listaprodutos.ListaProdutos
import com.devlucaslima.cashierapp.pdvmain.PDVMainActivity
import com.devlucaslima.cashierapp.listaprodutos.ListaUtils
import com.devlucaslima.cashierapp.pdvlista.PDVArrayLista
import com.devlucaslima.cashierapp.pdvlista.PDVListaUtils
import com.devlucaslima.cashierapp.settings.GlobalSettings
import com.devlucaslima.cashierapp.settings.SettingsActivity

class MainActivity : AppCompatActivity() {

    fun carregarString(context: Context, chave: String, valorPadrao: String): String {
        // Obtendo uma referência ao SharedPreferences
        val sharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)

        // Obtendo a string associada à chave, ou retornando o valor padrão se a chave não existir
        return sharedPreferences.getString(chave, valorPadrao) ?: valorPadrao
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentPDVMain = Intent(this, PDVMainActivity::class.java)
        val intentListaProdutos = Intent(this, ListaProdutos::class.java)
        val intentSettings = Intent(this, SettingsActivity::class.java)
        val btnPDV = findViewById<Button>(R.id.btnPDV)
        val btnListaProdutos = findViewById<Button>(R.id.btnListaProdutos)
        val btnSettings = findViewById<ImageButton>(R.id.btnSettings)
        val txtTitulo = findViewById<TextView>(R.id.txtTitulo)

        //Título
        var titulo = carregarString(this, "menuTitulo", "Cashier App")
        txtTitulo.text = titulo

        btnPDV.setOnClickListener{
            startActivity(intentPDVMain)
        }
        btnListaProdutos.setOnClickListener{
            startActivity(intentListaProdutos)
        }
        btnSettings.setOnClickListener{
            startActivity(intentSettings)
            finish()
        }

        val listaCarregada = ListaUtils.carregarListaProdutos(this)
        ArrayLista.listaProdutos = listaCarregada

        val pdvListaCarregada = PDVListaUtils.carregarListaProdutos(this)
        PDVArrayLista.pdvlistaProdutos = pdvListaCarregada
    }
}