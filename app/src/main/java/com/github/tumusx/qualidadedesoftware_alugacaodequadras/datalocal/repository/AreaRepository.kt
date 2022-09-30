package com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.repository

import com.github.tumusx.qualidadedesoftware_alugacaodequadras.common.ResultState
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.entity.Area
import kotlinx.coroutines.flow.Flow

interface AreaRepository {
    suspend fun listarAreas() : Flow<ResultState<List<Area>>>
    suspend fun criarArea(area: Area) : Flow<Boolean>
    suspend fun alugarArea(area: Area) : Flow<Boolean>
}