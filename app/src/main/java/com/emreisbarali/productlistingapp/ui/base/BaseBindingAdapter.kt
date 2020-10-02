package com.emreisbarali.productlistingapp.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseBindingAdapter<T, in DB : ViewDataBinding> :
    RecyclerView.Adapter<BaseBindingAdapter<T, DB>.BaseViewHolder>() {

    var items: List<T>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    open var itemClickListener: ((T) -> Unit)? = null

    abstract fun bindingVariableId(): Int

    abstract fun layoutRes(): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<DB>(layoutInflater, layoutRes(), parent, false)

        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        items?.let {
            val item = it[position]
            holder.bind(item)
            holder.itemView.setOnClickListener { itemClickListener?.invoke(item) }
        }
    }

    override fun getItemCount() = items?.size ?: 0

    inner class BaseViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T) {
            binding.setVariable(bindingVariableId(), item)
            binding.executePendingBindings()
        }
    }
}