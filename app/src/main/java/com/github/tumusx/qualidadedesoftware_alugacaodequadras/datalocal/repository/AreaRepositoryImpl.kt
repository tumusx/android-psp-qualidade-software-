package com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.repository

import com.github.tumusx.qualidadedesoftware_alugacaodequadras.common.ResultState
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.datasource.AreaDatasource
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.entity.Quadra
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AreaRepositoryImpl(private val database: AreaDatasource) : AreaRepository {

    override suspend fun listarAreas(): Flow<ResultState<List<Quadra>>> = flow {
        try {
            val listAreas = database.areaDAO().listarQuadras()
            emit(ResultState.SuccessResultDataSourceDataSourceUtil(listAreas))
        } catch (exception: Exception) {
            emit(ResultState.ErrorResultDataSourceDataSourceUtil(exception.message))
            exception.printStackTrace()
        }
    }

    override suspend fun criarArea(area: Quadra): Flow<Boolean> = flow {
        try {
            database.areaDAO().cadastrarQuadra(area)
            emit(true)
        } catch (exception: Exception) {
            emit(false)
            exception.printStackTrace()
        }
    }

    override suspend fun alugarArea(area: Quadra): Flow<Boolean> = flow{
        if(area.estaAlugada){
            emit(false)
        }else{
            database.areaDAO().alugarQuadra(area)
            emit(true)
        }
    }
}