package com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.repository

import com.github.tumusx.qualidadedesoftware_alugacaodequadras.common.ResultState
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.datasource.AreaDatasource
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.entity.Area
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AreaRepositoryImpl(private val database: AreaDatasource) : AreaRepository {

    override suspend fun listarAreas(): Flow<ResultState<List<Area>>> = flow {
        try {
            val listAreas = database.areaDAO().listarAreasCadastradas()
            emit(ResultState.SuccessResultDataSourceDataSourceUtil(listAreas))
        } catch (exception: Exception) {
            emit(ResultState.ErrorResultDataSourceDataSourceUtil(exception.message))
            exception.printStackTrace()
        }
    }

    override suspend fun criarArea(area: Area): Flow<Boolean> = flow {
        try {
            database.areaDAO().listarAreasCadastradas().forEach { area ->
                if (area.nomeArea?.equals(area.nomeArea) == true) {
                    emit(false)
                } else {
                    database.areaDAO().cadastrarArea(area)
                    emit(true)
                }
            }
        } catch (exception: Exception) {
            emit(false)
            exception.printStackTrace()
        }
    }

    override suspend fun alugarArea(area: Area): Flow<Boolean> = flow{
        if(area.quadra?.estaAlugada == true){
            emit(false)
        }else{
            database.areaDAO().alugarQuadra(area)
            emit(true)
        }
    }
}