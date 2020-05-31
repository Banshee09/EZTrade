package com.eztrade.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eztrade.database.getDatabase
import com.eztrade.domain.Order
import com.eztrade.network.EzTradeWs
import com.eztrade.repository.OrdersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.IOException

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val ordersRepository = OrdersRepository(database)
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val orders = ordersRepository.orders

    init {
        viewModelScope.launch {
            ordersRepository.refreshOrders()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    private val _navigateToDetail = MutableLiveData<Order>()
    val navigateToDetail
        get() = _navigateToDetail

    fun onOrderClicked(order: Order) {
        _navigateToDetail.value = order
    }

    fun onDetailNavigated() {
        _navigateToDetail.value = null
    }

}