package com.guilhermegalanti.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException
import java.lang.Math.round

class MainActivity : AppCompatActivity() {

    //TODO enxugar estas flags
    var ultimoEraPonto: Boolean = false
    var ultimoEraNumero: Boolean = true
    var apertarCLR: Boolean = true
    var inicio: Boolean = true

    private var tvTelaCalculadora: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTelaCalculadora = findViewById(R.id.tvTelaCalculadora)
    }

    fun cliclarTecla(view: View) {
        if (inicio) {
            tvTelaCalculadora?.text = ""
            inicio = false
        }

        tvTelaCalculadora?.append((view as Button).text) // Somente o botão tem a propriedade .text
        ultimoEraNumero = true
        ultimoEraPonto = false
    }

    fun teclaCLR(view: View) {
        tvTelaCalculadora?.text = "0"
        apertarCLR = true
        inicio = true
    }

    fun pontoDecimal(view: View) {
        if(ultimoEraNumero && !ultimoEraPonto && apertarCLR) {
            tvTelaCalculadora?.append((view as Button).text)
            ultimoEraNumero = false
            ultimoEraPonto = true
            apertarCLR = false
            inicio = false
        }
    }

    private fun operadorAdicionado(naTela: String): Boolean {
        return naTela.contains("+")
                || naTela.contains("-")
                || naTela.contains("*")
                || naTela.contains("/")
    }

    fun operacao(view: View){
        if(!inicio) {
            tvTelaCalculadora?.text.let {
                if (ultimoEraNumero && !operadorAdicionado(it.toString())) {
                    tvTelaCalculadora?.append((view as Button).text)
                    ultimoEraNumero = false
                    ultimoEraPonto = false
                }
            }
        }

    }

    fun teclaIgual(view: View) {
        if(ultimoEraNumero) {
            var textoNaTela = tvTelaCalculadora?.text.toString() // text é uma sequência de caracteres
            if(operadorAdicionado(textoNaTela)){
                try {
                    if(textoNaTela.contains("-")){
                        val splitText = textoNaTela.split("-")
                        val resultado = splitText[0].toInt() - splitText[1].toInt()
                        tvTelaCalculadora?.text = resultado.toString()
                        inicio = true
                    } else if(textoNaTela.contains("+")){
                        val splitText = textoNaTela.split("+")
                        val resultado = splitText[0].toInt() + splitText[1].toInt()
                        tvTelaCalculadora?.text = resultado.toString()
                        inicio = true
                    } else if(textoNaTela.contains("/")){
                        val splitText = textoNaTela.split("/")
                        val resultado = splitText[0].toDouble() / splitText[1].toDouble()
                        tvTelaCalculadora?.text = String.format("%.2f", resultado)
                        inicio = true
                    } else if(textoNaTela.contains("*")){
                        val splitText = textoNaTela.split("*")
                        val resultado = splitText[0].toInt() * splitText[1].toInt()
                        tvTelaCalculadora?.text = resultado.toString()
                        inicio = true
                    }
                } catch (e: ArithmeticException) {
                    e.printStackTrace()
                }
            }
        }
    }

}