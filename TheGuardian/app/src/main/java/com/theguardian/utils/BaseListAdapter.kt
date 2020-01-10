package com.theguardian.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.theguardian.BR
import java.util.*

open class BaseListAdapter<T> : ListAdapter<T, BaseListHolder<T>> {

    private var mEmptyTextView: TextView? = null
    private var mEmptyImageView: View? = null
    private var resLayoutID: Int = 0
    var onListItemClickListener: OnListItemClickListener<T>? = null

    constructor(resLayoutID: Int) : super(BaseDiffUtils<T>(null, ArrayList<T>())) {
        this.resLayoutID = resLayoutID
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListHolder<T> {
        return BaseListHolder(
            DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                viewType,
                parent,
                false
            )
        )
    }

    override fun getItemViewType(position: Int): Int {
        return resLayoutID
    }

    override fun getItemCount(): Int {
        if (mEmptyTextView != null) {
            if (super.getItemCount() <= 0) {
                mEmptyTextView!!.visibility = View.VISIBLE
                if (mEmptyImageView != null)
                    mEmptyImageView!!.visibility = View.VISIBLE
            } else {
                mEmptyTextView!!.visibility = View.GONE
                if (mEmptyImageView != null)
                    mEmptyImageView!!.visibility = View.GONE
            }
        }
        return super.getItemCount()
    }

    override fun onBindViewHolder(holder: BaseListHolder<T>, position: Int) {
        holder.bind(getItem(position))
        holder.binding.setVariable(BR.clicks, onListItemClickListener)
    }

    interface OnListItemClickListener<T> {
        fun onItemClick(view: View, itemData: T)
    }
}
