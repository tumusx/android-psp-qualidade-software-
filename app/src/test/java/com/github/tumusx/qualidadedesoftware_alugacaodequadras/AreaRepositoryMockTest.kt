package com.github.tumusx.qualidadedesoftware_alugacaodequadras

import com.github.tumusx.qualidadedesoftware_alugacaodequadras.common.ResultState
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.entity.Quadra
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.repository.AreaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AreaRepositoryMockImpl : AreaRepository {
    override suspend fun listarAreas(): Flow<ResultState<List<Quadra>>> = flow {
        try {
            val listQuadras = mutableListOf<Quadra>()
            listQuadras.add(Quadra(1, "20/10/2022", true, "IFG - Quadra 8", "12:30", "12"))
            emit(
                ResultState.SuccessResultDataSourceDataSourceUtil(listQuadras)
            )
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    override suspend fun criarArea(area: Quadra): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun alugarArea(area: Quadra): Flow<Boolean> {
        TODO("Not yet implemented")
    }

}