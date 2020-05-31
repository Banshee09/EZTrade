package com.eztrade.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eztrade.domain.Order

class DetailViewModel : ViewModel() {

    private val _order = MutableLiveData<Order>()

    val order: LiveData<Order> = _order

    fun setOrder(order: Order) {
        _order.value = order
    }
}
