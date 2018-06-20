package givaldoms.calculadoralibras

import java.util.*

/**
 * Created by givaldoms on 19/06/2018.
 */

data class Calculadora(
        var numero1: Int = 0,
        var numero2: Int = 0,
        var operacao: Int = 0/*0 => +, 1 => -, 2 => * */) {


    fun gerarValores() {
        val r = Random()
        this.numero1 = r.nextInt(9)
        this.numero2 = r.nextInt(9)
        this.operacao = r.nextInt(3)
    }


    fun isRespostaCorreta(resposta: Int) = when (operacao) {
        0 -> {
            (numero1 + numero2) == resposta
        }

        1 -> {
            true
            //resultado = numero1 + numero2
        }

        2 -> {
            (numero1 * numero2) == resposta
        }
        else -> {
            true
        }
    }

    fun getSinalOperacao() = when (this.operacao) {
        0 -> '+'
        1 -> '-'
        2 -> '*'
        else -> throw Exception("Operação inválida")

    }

}
