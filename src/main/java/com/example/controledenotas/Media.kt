package com.example.controledenotas

 fun situacaoAluno(media: Double): String {
//        Operador ternário
    return if(media >= 5) "Aprovado" else "Reprovado"
}
fun calcularMedia(vararg notas: Double): Double {
    var soma = 0.0
    for(nota in notas) {
        soma += nota
    }

    return soma / notas.size
}