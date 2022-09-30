package com.github.tumusx.qualidadedesoftware_alugacaodequadras.presenter.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.R
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.databinding.ContainerFragmentFirstBinding
import com.github.tumusx.qualidadedesoftware_alugacaodequadras.datalocal.entity.Quadra

class ListQuadraAdapter(private val callback: (Quadra) -> Unit) : RecyclerView.Adapter<ListQuadraAdapter.ListaQuadraViewHolder>() {
    var quadrasList = mutableListOf<Quadra>()

    class ListaQuadraViewHolder(val binding: ContainerFragmentFirstBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun configUI(quadra: Quadra) {
            binding.nomeQuadra.text = quadra.nomeQuadra
            binding.horarioUltimaAlugacao.text = quadra.horaUltimaAlugacao
            binding.lastAlugacao.text = quadra.dataUltimaAlugacao
            if(quadra.estaAlugada) binding.isAlugada.setTextColor(R.color.red)
            binding.quantidadeQuePodeOcupar.text = quadra.quantidadeOcupacao.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaQuadraViewHolder {
        return ListaQuadraViewHolder(ContainerFragmentFirstBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ListaQuadraViewHolder, position: Int) {
        holder.configUI(quadrasList[position])
        holder.binding.root.setOnClickListener {
            callback.invoke(quadrasList[position])
        }
    }

    override fun getItemCount() = quadrasList.size

     fun updateList(quadra: List<Quadra>) {
        quadrasList = quadra as MutableList<Quadra>
        notifyDataSetChanged()
    }
}