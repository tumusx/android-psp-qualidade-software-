package com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.entity

data class Quadra(
    var dataUltimaAlugacao: String? = null,
    var estaAlugada: Boolean = false,
    var horaUltimaAlugacao: String? = null,
    var quantidadeOcupacao: Int? = null
)
