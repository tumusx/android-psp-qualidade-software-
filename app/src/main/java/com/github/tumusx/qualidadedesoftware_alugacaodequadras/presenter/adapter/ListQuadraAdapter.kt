package com.github.tumusx.qualidadedesoftware_alugacaodequadras.presenter.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.R
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.common.DiffUtilClass
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.databinding.ContainerFragmentFirstBinding
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.entity.Quadra

class ListQuadraAdapter(private val callback: (Quadra) -> Unit) :
    RecyclerView.Adapter<ListQuadraAdapter.ListaQuadraViewHolder>() {
    var quadrasList = emptyList<Quadra>()

    class ListaQuadraViewHolder(val binding: ContainerFragmentFirstBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor", "SetTextI18n")
        fun configUI(quadra: Quadra) {
            binding.nomeQuadra.text = quadra.nomeQuadra
            if (quadra.horaUltimaAlugacao?.isNotEmpty() == true && quadra.dataUltimaAlugacao?.isNotEmpty() == true) {
                binding.horarioUltimaAlugacao.text =
                    binding.root.context.getString(R.string.horario_alugacao) + " " + quadra.horaUltimaAlugacao
                binding.lastAlugacao.text =
                    binding.root.context.getString(R.string.dia_alugacao) + " " + quadra.dataUltimaAlugacao
            }
            if (quadra.estaAlugada) {
                binding.isAlugada.text = binding.root.context.getString(R.string.esta_alugada)
                binding.isAlugada.setTextColor(R.color.red)
            }
            binding.quantidadeQuePodeOcupar.text =
                binding.root.context.getString(R.string.qntdAlugacao) + " " + quadra.quantidadeOcupacao.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaQuadraViewHolder {
        return ListaQuadraViewHolder(
            ContainerFragmentFirstBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ListaQuadraViewHolder, position: Int) {
        holder.configUI(quadrasList[position])
        holder.binding.root.setOnClickListener {
            callback.invoke(quadrasList[position])
        }
    }

    override fun getItemCount() = quadrasList.size

    fun updateList(listNewItems: List<Quadra>) {
        val myDiffUtil = DiffUtilClass(quadrasList, listNewItems)
        val diffResults = DiffUtil.calculateDiff(myDiffUtil)
        quadrasList = listNewItems
        diffResults.dispatchUpdatesTo(this)
    }
}