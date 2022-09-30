package com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quadra")
data class Quadra(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_quadra")
    var idArea: Long? = null,

    @ColumnInfo(name = "dataUltimaAlugacao")
    var dataUltimaAlugacao: String? = null,

    @ColumnInfo(name = "estaAlugada")
    var estaAlugada: Boolean = false,

    @ColumnInfo(name = "nomeQuadra")
    var nomeQuadra: String? = null,

    @ColumnInfo(name = "horaUltimaAlugacao")
    var horaUltimaAlugacao: String? = null,

    @ColumnInfo(name = "quantidadeOcupacao")
    var quantidadeOcupacao: Int? = null
)
