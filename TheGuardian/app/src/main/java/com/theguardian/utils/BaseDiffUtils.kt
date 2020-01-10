package com.theguardian.utils

import java.util.ArrayList
import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtils<T>(internal var oldList: ArrayList<T>?, internal var newList: ArrayList<T>) :
    DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}
