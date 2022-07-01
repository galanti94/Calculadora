package com.guilhermegalanti.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvTelaCalculadora: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTelaCalculadora = findViewById(R.id.tvTelaCalculadora)
    }

    fun cliclarTecla(view: View) {
        tvTelaCalculadora?.append((view as Button).text) // Somente o botão tem a propriedade .text
    }
}