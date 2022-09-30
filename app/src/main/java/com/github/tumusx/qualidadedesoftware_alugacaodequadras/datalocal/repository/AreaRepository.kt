package com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.repository

import com.github.tumusx.qualidadedesoftware_alugacaodequadras.common.ResultState
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.entity.Quadra
import kotlinx.coroutines.flow.Flow

interface AreaRepository {
    suspend fun listarAreas() : Flow<ResultState<List<Quadra>>>
    suspend fun criarArea(area: Quadra) : Flow<Boolean>
    suspend fun alugarArea(area: Quadra) : Flow<Boolean>
}