package givaldoms.calculadoralibras

import org.junit.Before
import org.junit.Test

/**
 * Created by givaldoms on 19/06/2018.
 */
class CalculadoraTest {
    lateinit var mCalculadora: Calculadora

    @Before
    fun init() {
        mCalculadora = Calculadora()
    }

    @Test
    fun gerarValores() {
        mCalculadora.gerarValores()
        println(mCalculadora.toString())
    }

}