package com.github.tumusx.qualidadedesoftware_alugacaodequadras.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.R
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.databinding.FragmentSecondBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CadastrarQuadras : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        binding = FragmentSecondBinding.inflate(inflater, container, false)
        createAreaRouteNavigation()
        return binding.root
    }

    private fun createAreaRouteNavigation() {
        binding.btnCreateUser.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

}