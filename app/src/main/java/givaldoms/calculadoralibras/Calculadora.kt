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
        this.operacao = r.nextInt(3)

        this.numero1 = r.nextInt(9)
        this.numero2 = r.nextInt(9)

        if (operacao == 1) {//subtração
            while (this.numero2 > this.numero1) {
                this.numero1 = r.nextInt(9)
                this.numero2 = r.nextInt(9)
            }
        }

    }

    fun getRespostaCorreta() = when (operacao) {
        0 -> {
            numero1 + numero2
        }

        1 -> {
            numero1 - numero2
        }

        2 -> {
            numero1 * numero2
        }

        else -> throw Exception("$operacao deve ser um valor entre 0 e 2")

    }


    fun isRespostaCorreta(resposta: Int) = getRespostaCorreta() == resposta

    fun getSinalOperacao() = when (this.operacao) {
        0 -> '+'
        1 -> '-'
        2 -> '*'
        else -> throw Exception("Operação inválida")

    }

}
