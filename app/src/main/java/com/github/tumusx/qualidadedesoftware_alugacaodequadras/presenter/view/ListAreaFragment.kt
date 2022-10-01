package com.github.tumusx.qualidadedesoftware_alugacaodequadras.presenter.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.R
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.databinding.DialogConfirmarAlugamentoBinding
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.databinding.FragmentFirstBinding
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.entity.Quadra
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.presenter.adapter.ListQuadraAdapter
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.presenter.state.StateAreas
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.presenter.viewModel.AreaViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListAreaFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private lateinit var quadraAdapter: RecyclerView.Adapter<*>
    private val listAreViewModel by viewModel<AreaViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(layoutInflater)
        inicializarAdapter()
        updateListAdapter()
        configRefresh()
        return binding.root
    }

    private fun configRefresh() {
        binding.swiperefresh.setOnRefreshListener {
            lifecycleScope.launch {
                delay(1000)
                listAreViewModel.listarQuadras()
                inicializarAdapter()
                binding.swiperefresh.isRefreshing = false
            }
        }
    }

    private fun configConfirmCheckBoxDialog(context: Context, quadra: Quadra) {
        val dialogBinding = DialogConfirmarAlugamentoBinding.inflate(LayoutInflater.from(context))
        val dialog = context.let {
            AlertDialog.Builder(context).setCancelable(true).setView(dialogBinding.root)
        }?.create()
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        if (quadra.estaAlugada) {
            dialogBinding.alugarQuadra.text = requireContext().getString(R.string.liberarQuadra)
            dialogBinding.diaAlugarQuadra.visibility = View.GONE
            dialogBinding.horarioAlugar.visibility = View.GONE
            dialogBinding.btnSave.setOnClickListener {
                quadra.estaAlugada = false
                quadra.dataUltimaAlugacao = ""
                quadra.horaUltimaAlugacao = ""
                listAreViewModel.alugarQuadra(quadra)
                dialog?.dismiss()
            }
        } else {
            dialogBinding.btnSave.setOnClickListener {
                if (dialogBinding.horarioAlugar.text.toString()
                        .isNotEmpty() && dialogBinding.horarioAlugar.text.toString().isNotEmpty()
                ) {
                    quadra.estaAlugada = true
                    quadra.dataUltimaAlugacao = dialogBinding.diaAlugarQuadra.text.toString()
                    quadra.horaUltimaAlugacao = dialogBinding.horarioAlugar.text.toString()
                    listAreViewModel.alugarQuadra(quadra)
                    dialog?.dismiss()
                } else {
                    Snackbar.make(
                        dialogBinding.btnSave.rootView,
                        requireContext().getString(R.string.informe_os_dados),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.show()
    }

    @SuppressLint("ResourceAsColor")
    private fun inicializarAdapter() {
        binding.progressBar.visibility = View.VISIBLE
        quadraAdapter = ListQuadraAdapter {
            configConfirmCheckBoxDialog(requireContext(), it)
        }
        binding.rvQuadras.adapter = (quadraAdapter as ListQuadraAdapter)
    }

    private fun updateListAdapter() {
        viewLifecycleOwner.lifecycle.coroutineScope.launch {
            listAreViewModel.valuesState.collect { state ->
                when (state) {
                    is StateAreas.SuccessListArea -> {
                        binding.progressBar.visibility = View.GONE
                        println(state.areas)
                        if (state.areas?.isEmpty() == true)
                            binding.llContainerItems.visibility = View.VISIBLE
                        else
                            binding.llContainerItems.visibility = View.GONE
                        state.areas?.let { (quadraAdapter as ListQuadraAdapter).updateList(it) }
                    }

                    is StateAreas.ErrorListArea -> {
                        println(state.messageError)
                        binding.progressBar.visibility = View.GONE
                        binding.llContainerItems.visibility = View.GONE
                        Snackbar.make(binding.root, state.messageError, Snackbar.LENGTH_SHORT)
                            .show()
                    }

                    is StateAreas.IsLoadingArea -> {
                        println("LOADING")
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        }
    }


}