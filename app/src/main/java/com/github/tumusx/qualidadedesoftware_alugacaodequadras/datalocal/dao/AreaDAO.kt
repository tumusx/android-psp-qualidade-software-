package com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.entity.Area

interface AreaDAO {

    @Query("SELECT *FROM area")
    suspend fun listarAreasCadastradas() : List<Area>

    @Insert
    suspend fun cadastrarArea(vararg area: Area)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun alugarQuadra(vararg area: Area)
}