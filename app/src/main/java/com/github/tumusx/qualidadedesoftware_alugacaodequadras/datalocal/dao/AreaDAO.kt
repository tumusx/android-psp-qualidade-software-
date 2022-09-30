package com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.dao

import androidx.room.*
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.entity.Quadra
@Dao
interface AreaDAO {

    @Query("SELECT *FROM quadra")
    suspend fun listarQuadras() : List<Quadra>

    @Insert
    suspend fun cadastrarQuadra(vararg quadra: Quadra)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun alugarQuadra(vararg quadra: Quadra)
}