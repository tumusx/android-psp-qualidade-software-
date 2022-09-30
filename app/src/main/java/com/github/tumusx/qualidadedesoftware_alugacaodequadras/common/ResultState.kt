package com.github.tumusx.qualidadedesoftware_alugacaodequadras.common

sealed class ResultState<T>(val message: String? = null, val dataResult: T? = null) {
    class SuccessResultDataSourceDataSourceUtil<T>(dataResult: T?) : ResultState<T>(dataResult = dataResult)
    class ErrorResultDataSourceDataSourceUtil<T>(message: String? = null, dataResult: T? = null) : ResultState<T>(message, dataResult)
}