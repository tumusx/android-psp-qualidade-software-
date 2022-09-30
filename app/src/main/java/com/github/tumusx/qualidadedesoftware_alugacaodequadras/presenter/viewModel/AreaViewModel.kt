package com.github.tumusx.qualidadedesoftware_alugacaodequadras.presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.R
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.common.ResultState
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.entity.Area
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.repository.AreaRepositoryImpl
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.presenter.state.StateAreas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AreaViewModel(private val areaRepository: AreaRepositoryImpl) : ViewModel() {
    private val _values: MutableStateFlow<StateAreas> = MutableStateFlow(StateAreas.IsLoadingArea)
    private val _alugarAreaResult: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val alugarAreaResult: StateFlow<Boolean> = _alugarAreaResult
    val valuesState: StateFlow<StateAreas> = _values

    init {
        listarQuadras()
    }

    private fun listarQuadras() {
        viewModelScope.launch(Dispatchers.IO) {
            _values.value = StateAreas.IsLoadingArea
            areaRepository.listarAreas().collect { resultState ->
                when (resultState) {
                    is ResultState.SuccessResultDataSourceDataSourceUtil -> {
                        _values.value = StateAreas.SuccessListArea(resultState.dataResult)
                    }
                    is ResultState.ErrorResultDataSourceDataSourceUtil -> {
                        _values.value = StateAreas.ErrorListArea(R.string.error_list_quadras)
                    }
                }
            }
        }
    }


    fun alugarQuadra(area: Area) {
        viewModelScope.launch(Dispatchers.IO) {
            areaRepository.alugarArea(area).collect{isResult->
                _alugarAreaResult.value = isResult
            }
        }
    }
}