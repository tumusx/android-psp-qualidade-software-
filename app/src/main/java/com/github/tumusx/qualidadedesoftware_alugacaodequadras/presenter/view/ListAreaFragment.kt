package com.github.tumusx.qualidadedesoftware_alugacaodequadras.presenter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.databinding.FragmentFirstBinding
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.presenter.adapter.ListQuadraAdapter
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.presenter.state.StateAreas
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.presenter.viewModel.AreaViewModel
import com.google.android.material.snackbar.Snackbar
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
        return binding.root
    }

    private fun inicializarAdapter() {
        binding.progressBar.visibility = View.VISIBLE
        quadraAdapter = ListQuadraAdapter {
            println(it)
        }
        binding.rvQuadras.adapter = (quadraAdapter as ListQuadraAdapter)
        viewLifecycleOwner.lifecycle.coroutineScope.launch {
            listAreViewModel.valuesState.collect { state ->
                when (state) {
                    is StateAreas.SuccessListArea -> {
                        binding.progressBar.visibility = View.GONE
                        println(state.areas)
                        state.areas?.let { (quadraAdapter as ListQuadraAdapter).updateList(it) }
                    }

                    is StateAreas.ErrorListArea -> {
                        println(state.messageError)
                        binding.progressBar.visibility = View.GONE
                        Snackbar.make(binding.root, state.messageError, Snackbar.LENGTH_SHORT).show()
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