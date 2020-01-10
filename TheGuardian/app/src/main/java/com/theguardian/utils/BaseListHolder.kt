package com.theguardian.utils

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.theguardian.BR


class BaseListHolder<T>(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    var binding: ViewDataBinding
        internal set

    init {
        this.binding = binding
    }

    fun bind(item: T) {
        binding.setVariable(BR.data, item)
        binding.executePendingBindings()
    }

}