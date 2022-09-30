package com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.dao.AreaDAO
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.entity.Quadra

@Database(entities = [Quadra::class], version = 2, exportSchema = false)
abstract class AreaDatasource : RoomDatabase() {
    abstract fun areaDAO() : AreaDAO
}