package com.eztrade.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eztrade.databinding.ListItemHoldingBinding
import com.eztrade.domain.Holding
import com.eztrade.domain.Order
import java.util.concurrent.ThreadLocalRandom


class HoldingAdapter : ListAdapter<Holding, HoldingAdapter.ViewHolder>(HoldingDiffCallback()) {

    class ViewHolder private constructor(val binding: ListItemHoldingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Holding) {
            binding.holding = item
            binding.executePendingBindings()
        }

        companion object {
            public fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemHoldingBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: HoldingAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoldingAdapter.ViewHolder {
        return HoldingAdapter.ViewHolder.from(parent)
    }
}

class HoldingDiffCallback : DiffUtil.ItemCallback<Holding>() {
    override fun areItemsTheSame(oldItem: Holding, newItem: Holding): Boolean {
        return oldItem.holdingId == newItem.holdingId
    }

    override fun areContentsTheSame(oldItem: Holding, newItem: Holding): Boolean {
        return oldItem == newItem
    }
}

@BindingAdapter("assetString")
fun TextView.setAssetString(item: Holding?) {
    item?.let {
        text = (item.asset)
    }
}

@BindingAdapter("unitString")
fun TextView.setUnitString(item: Holding?) {
    item?.let {
        text = "Unit: " + (item.unit.toString())
    }
}

@BindingAdapter("priceString")
fun TextView.setPriceString(item: Holding?) {
    item?.let {
        text = "Price: $" + item.price.toString()
    }
}

@BindingAdapter("totalString")
fun TextView.setTotalString(item: Holding?) {
    item?.let {
        text = "Value: $" + (item.unit * item.price).toString()
    }
}