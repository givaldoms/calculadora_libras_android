package givaldoms.calculadoralibras

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val calculadora by lazy {
        Calculadora()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        responderButton.setOnClickListener {
            responder()
        }

        configurarBotoes()
        atualizarView()

    }

    private fun configurarBotoes() {

        umImageButton.setOnClickListener {
            respostaTextView.concatenar("1")
        }

        doisImageButton.setOnClickListener {
            respostaTextView.concatenar("2")
        }

        tresImageButton.setOnClickListener {
            respostaTextView.concatenar("3")
        }

        quatroImageButton.setOnClickListener {
            respostaTextView.concatenar("4")
        }

        cincoImageButton.setOnClickListener {
            respostaTextView.concatenar("5")
        }

        seisImageButton.setOnClickListener {
            respostaTextView.concatenar("6")
        }

        seteImageButton.setOnClickListener {
            respostaTextView.concatenar("7")
        }

        oitoImageButton.setOnClickListener {
            respostaTextView.concatenar("8")
        }

        noveImageButton.setOnClickListener {
            respostaTextView.concatenar("9")
        }

        zeroImageButton.setOnClickListener {
            respostaTextView.concatenar("0")
        }

    }

    private fun atualizarView() {
        calculadora.gerarValores()

        respostaTextView.text = ""

        sinalTextView.text = calculadora.getSinalOperacao().toString()

        val drawableValor1 = getLibrasDrawable(calculadora.numero1)
        val drawableValor2 = getLibrasDrawable(calculadora.numero2)

        valor1ImageView.setImageDrawable(drawableValor1)
        valor2ImageView.setImageDrawable(drawableValor2)

    }

    private fun responder() {
        val resposta = respostaTextView.text.toString().toIntOrNull() ?: return

        Log.d("MainActivity",
                "${calculadora.numero1} ${calculadora.getSinalOperacao()}" +
                        " ${calculadora.numero2} = ${calculadora.getRespostaCorreta()}")

        if (calculadora.isRespostaCorreta(resposta)) {
            Toast.makeText(this, "Resposta correta", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Resposta incorreta", Toast.LENGTH_SHORT).show()
        }
        atualizarView()
    }

    private fun getLibrasDrawable(number: Int) = when (number) {
        0 -> getDrawable(R.drawable.img_libras_zero)
        1 -> getDrawable(R.drawable.img_libras_um)
        2 -> getDrawable(R.drawable.img_libras_dois)
        3 -> getDrawable(R.drawable.img_libras_tres)
        4 -> getDrawable(R.drawable.img_libras_quatro)
        5 -> getDrawable(R.drawable.img_libras_cinco)
        6 -> getDrawable(R.drawable.img_libras_seis)
        7 -> getDrawable(R.drawable.img_libras_sete)
        8 -> getDrawable(R.drawable.img_libras_oito)
        9 -> getDrawable(R.drawable.img_libras_nove)
        else -> throw Exception("$number deve ser um valor entre 0 e 9")
    }

    private fun TextView.concatenar(valor: String) {
        val c = "${this.text}$valor"
        this.text = c
    }
}
