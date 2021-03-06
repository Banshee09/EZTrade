package com.eztrade.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eztrade.R
import com.eztrade.databinding.ListItemOrderBinding
import com.eztrade.domain.Order


class OrderAdapter(val clickListener: OrderListener) :
    ListAdapter<Order, OrderAdapter.ViewHolder>(OrderDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position)!!)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: OrderListener, item: Order) {
            binding.order = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            public fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemOrderBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

}

class OrderDiffCallback : DiffUtil.ItemCallback<Order>() {
    override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem.orderId == newItem.orderId
    }

    override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem == newItem
    }
}

@BindingAdapter("instructionImage")
fun ImageView.setInstruction(item: Order?) {
    item?.let {
        if (item.instruction == "BUY")
            setImageResource(R.drawable.ic_baseline_add_circle_outline_24)
        else
            setImageResource(R.drawable.ic_baseline_remove_circle_outline_24)
    }
}

@BindingAdapter("assetString")
fun TextView.setAssetString(item: Order?) {
    item?.let {
        text = (item.asset)
    }
}

@BindingAdapter("unitString")
fun TextView.setQuantityString(item: Order?) {
    item?.let {
        text = (item.unit.toString())
    }
}

@BindingAdapter("totalString")
fun TextView.setTotalString(item: Order?) {
    item?.let {
        text = "$" + (item.total.toString())
    }
}

class OrderListener(val clickListener: (order: Order) -> Unit) {
    fun onClick(order: Order) = clickListener(order)
}