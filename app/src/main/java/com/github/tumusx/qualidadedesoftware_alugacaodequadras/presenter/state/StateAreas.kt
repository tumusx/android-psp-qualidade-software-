package com.github.tumusx.qualidadedesoftware_alugacaodequadras.presenter.state

import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.entity.Quadra

sealed class StateAreas {
    data class SuccessListArea(val areas: List<Quadra>?) : StateAreas()
    data class ErrorListArea(val messageError: Int) : StateAreas()
    object  IsLoadingArea : StateAreas()
}