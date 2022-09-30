package com.github.tumusx.qualidadedesoftware_alugacaodequadras.presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.entity.Quadra
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.repository.AreaRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CadastroQuadraViewModel(private val areaRepositoryImpl: AreaRepositoryImpl) : ViewModel() {
    private val _state: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val state: StateFlow<Boolean> = _state

    fun cadastrarAreas(area: Quadra) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                areaRepositoryImpl.criarArea(area).collect{isResult->
                    _state.value = isResult
                }
            }catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }

}