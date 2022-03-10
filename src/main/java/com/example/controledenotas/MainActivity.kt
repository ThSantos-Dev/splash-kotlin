package com.example.controledenotas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

/*   Criando uma variavel que possui todo o escopo da class, onde só irá ser inicializada após a criação da View (linha 15)   */
private lateinit var nomeEditText: EditText
private lateinit var nota1EditText: EditText
private lateinit var nota2EditText: EditText


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*   Recuperando elementos da View
        *   val nomeConst findViewById<tipoDoElemento>(R.id.nomeId)
        *    */
        val calcular = findViewById<Button>(R.id.calcular)
        val sair = findViewById<Button>(R.id.sair)

        /*  Resgatando o valor das 'notas' do View  */
        calcular.setOnClickListener{
            nota1EditText = findViewById(R.id.nota1)
            nota2EditText = findViewById(R.id.nota2)
            nomeEditText = findViewById(R.id.nome)
            val resultado = findViewById<TextView>(R.id.resultado)
            val relatorio = findViewById<TextView>(R.id.relatorio)

//            Validando campos
            if(validarCampos()) {
                val nota1 = nota1EditText.text.toString().toDouble()
                val nota2 = nota2EditText.text.toString().toDouble()
                val nome = nomeEditText.text.toString()

                val media = calcularMedia(nota1, nota2, 14.0, 32.0)

                /*
                * O intent é uma classe que fica responsável por chamar a próxima Activity
                * Ou repassar um valor de uma classe para outra
                *
                * Parâmetros
                * 1ª packageAtual (this)
                * 2º Activity a chamar::class.java
                * */
                val intent = Intent(this, RelatorioActivity::class.java)
                intent.putExtra("nome", nome)
                intent.putExtra("nota1", "${nota1}")
                intent.putExtra("nota2", "${nota2}")
                intent.putExtra("media", "${media}")
                intent.putExtra("situacao", situacaoAluno(media))

                startActivity(intent)

                /*resultado.text = sitacaoAluno(media)*/
            }

        }

        /*   Disponibilizando a aplicação para "fechar", gerenciado pelo Android   */
        sair.setOnClickListener{
            finish()
        }
    }


/* Maneira tradicinal

  private fun calcularMedia(nota1: Int, nota2: Int): Int {
        return (nota1 + nota2) / 2
    }*/


//    Maneira curta
//    private fun calcularMedia(nota1: Int, nota2: Int) = (nota1 + nota2) / 2

/*
//    Arrow funciton
    val calcularMedia = {nota1: Int, nota2: Int -> (nota1 + nota2) / 2
    }
*/

//    vararg - diz que pode receber N parâmetros, se tornará um Array consequêntemente



//    private fun calcularMedia(nota1: Double, nota2: Double) = (nota1 + nota2) / 2


    private fun validarCampos(): Boolean {
        var noError = true

        if(nomeEditText.text.isBlank()){
            nomeEditText.setError("Digite seu nome!")
            noError = false
        }

        if (nota1EditText.text.isBlank()) {
            nota1EditText.setError("Digite a nota 1!")
            noError = false
        }

        if (nota2EditText.text.isBlank()) {
            nota2EditText.setError("Digite a nota 2!")
            noError = false
        }

        return noError
    }
}