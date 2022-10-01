package com.github.tumusx.qualidadedesoftware_alugacaodequadras.presenter.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.R
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.databinding.FragmentCadastrarQuadraBinding
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.entity.Quadra
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.presenter.viewModel.CadastroQuadraViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
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

    @SuppressLint("ResourceAsColor")
    private fun cadastrarQuadras() {
        binding.btnSave.setOnClickListener {
            quadraDTO.nomeQuadra = binding.nomeQuadra.text.toString()
            quadraDTO.quantidadeOcupacao = binding.qntdOcupar.text.toString()
            if (quadraDTO.nomeQuadra?.isNotEmpty() == true || quadraDTO.quantidadeOcupacao?.isNotEmpty() == true) {
                viewModel.cadastrarAreas(quadraDTO)
                dismiss()
            }else{
                Snackbar.make(binding.btnSave.rootView, requireContext().getString(R.string.informe_os_dados),  Snackbar.LENGTH_SHORT).setTextColor(R.color.red).show()
            }
        }
    }

}