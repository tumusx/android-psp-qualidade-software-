package com.github.tumusx.qualidadedesoftware_alugacaodequadras

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.entity.Quadra
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.repository.AreaRepositoryImpl
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.presenter.viewModel.AreaViewModel
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@MockK
class AreaViewModelTest {

    @get:Rule
    val ruleGet = InstantTaskExecutorRule()

    @MockK
    @get:Before
    val repositoryMockk = mockk<AreaRepositoryImpl>()

    @MockK
    private lateinit var viewModel: AreaViewModel

    @Test
    fun getAllNamesById() {
        viewModel = AreaViewModel(repositoryMockk)
        viewModel.listarQuadras()
        val listQuadras = mutableListOf<Quadra>()
        listQuadras.add(Quadra(1, "20/10/2022", true, "IFG - Quadra 8", "12:30", "12"))
        verify {
            GlobalScope.launch {
                repositoryMockk.listarAreas().collect {
                    it.dataResult?.equals(listQuadras)
                }
            }
        }
    }
}