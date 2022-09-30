package com.github.tumusx.qualidadedesoftware_alugacaodequadras.presenter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.databinding.FragmentCadastrarQuadraBinding
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.entity.Quadra
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.presenter.viewModel.CadastroQuadraViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CadastrarQuadraFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentCadastrarQuadraBinding
    private val quadraDTO = Quadra()
    private val viewModel by viewModel<CadastroQuadraViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCadastrarQuadraBinding.inflate(layoutInflater)
        cadastrarQuadras()
        return binding.root
    }

    private fun cadastrarQuadras() {
        binding.btnSave.setOnClickListener {
            quadraDTO.nomeQuadra = "Quadra IFG Silvio"
            quadraDTO.quantidadeOcupacao = 12
            viewModel.cadastrarAreas(quadraDTO)
            dismiss()
        }
    }

}