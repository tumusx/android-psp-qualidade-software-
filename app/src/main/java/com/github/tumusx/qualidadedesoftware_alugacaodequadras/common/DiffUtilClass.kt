package com.github.tumusx.qualidadedesoftware_alugacaodequadras.common

import androidx.recyclerview.widget.DiffUtil

class DiffUtilClass<T>(private val oldItem: List<T>, private val newItem: List<T>) : DiffUtil.Callback(){
    override fun getOldListSize() = oldItem.size

    override fun getNewListSize() = newItem.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldItem[oldItemPosition] == newItem[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemPosition == newItemPosition
    }

}