package givaldoms.calculadoralibras

import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert


const val TEMPO_MAXIMO = 60// em segundos

class MainActivity : AppCompatActivity() {

    private val calculadora by lazy {
        Calculadora()
    }

    private var mTempoAtual = 120
    private var mPontuacao = 0
    private lateinit var mContador: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniciaContador()

        timeProgressBar.max = TEMPO_MAXIMO - 1
    }

    override fun onStart() {
        super.onStart()

        responderButton.setOnClickListener {
            responder()
            novosValores()
        }

        apagarButton.setOnClickListener {
            respostaTextView.apagarUltimo()
        }

        configurarBotoes()
        novoJogo()
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

    private fun novosValores() {
        calculadora.gerarValores()
        respostaTextView.text = ""

        sinalTextView.text = calculadora.getSinalOperacao().toString()

        val drawableValor1 = getLibrasDrawable(calculadora.numero1)
        val drawableValor2 = getLibrasDrawable(calculadora.numero2)

        valor1ImageView.setImageDrawable(drawableValor1)
        valor2ImageView.setImageDrawable(drawableValor2)

        atualizarPontuacao()
    }

    private fun novoJogo() {
        novosValores()
        mPontuacao = 0
        mTempoAtual = 120
        mContador.cancel()
        mContador.start()
        atualizarPontuacao()

    }

    override fun onStop() {
        super.onStop()
        mContador.cancel()
    }

    private fun responder() {
        val resposta = respostaTextView.text.toString().toIntOrNull() ?: return

        Log.d("MainActivity",
                "${calculadora.numero1} ${calculadora.getSinalOperacao()}" +
                        " ${calculadora.numero2} = ${calculadora.getRespostaCorreta()}")

        if (calculadora.isRespostaCorreta(resposta)) {
            mPontuacao++
        } else {
            Toast.makeText(this, "Resposta incorreta", Toast.LENGTH_SHORT).show()
        }
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

    private fun TextView.apagarUltimo() {
        val t = this.text

        when {
            t.isEmpty() -> return

            t.length == 1 -> {
                this.text = Editable.Factory.getInstance().newEditable("")
            }

            else -> {
                val t2 = t.subSequence(0, t.length - 1)
                this.text = Editable.Factory.getInstance().newEditable(t2)

            }
        }

    }

    private fun atualizarPontuacao() {
        val pontuacaoText = "Pontuação: $mPontuacao"
        pontuacaoTextView.text = pontuacaoText
    }

    private fun iniciaContador() {
        mContador = object : CountDownTimer(TEMPO_MAXIMO * 1000.toLong(), 1000) {

            override fun onTick(millisUntilFinished: Long) {
                val time = (millisUntilFinished / 1000).toInt()
                val t = "Tempo restante: $time"

                timeProgressBar.progress = time
                tempoTextView.text = t
            }

            override fun onFinish() {
                alert("Sua pontuação: $mPontuacao",
                        "Tempo esgotado") {
                    isCancelable = false
                    positiveButton("Novo jogo") {
                        novoJogo()
                    }
                }.show()

            }
        }

    }
}
