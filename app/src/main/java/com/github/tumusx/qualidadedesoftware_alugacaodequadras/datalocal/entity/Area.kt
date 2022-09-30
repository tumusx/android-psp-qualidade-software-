package com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "area")
data class Area(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_area")
    var idArea: Long? = null,

    @ColumnInfo(name = "nome_area")
    var nomeArea: String? = null,

    @ColumnInfo(name = "qntd_suportar_area")
    var qntdSuportar: Int? = null,

    @ColumnInfo(name = "quadra")
    var quadra: Quadra? = null
)