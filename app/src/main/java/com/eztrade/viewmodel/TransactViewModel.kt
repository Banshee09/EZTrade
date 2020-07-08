package com.eztrade.viewmodel


import android.util.Log
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eztrade.database.getDatabase
import com.eztrade.network.*
import com.eztrade.repository.OrdersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class TransactViewModel : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val instruction = MutableLiveData<String>().apply {
        value = "BUY"
    }

    val asset = MutableLiveData<String>()
    val unit = MutableLiveData<String>()
    val price = MutableLiveData<Double>().apply {
        value = 0.0
    }
    val total = MutableLiveData<Double>().apply {
        value = 0.0
    }

    public fun fillOrder() {
//        viewModelScope.launch {
//            val price = asset.value?.let { PriceWs.priceService.getLatestPrice(it) }
//            Log.d("TransactViewModel", price.toString())
//        }
        val networkPriceResponse = NetworkPriceResponse(NetworkStatus(200, ""), NetworkPrice(12.0))
        price.value = networkPriceResponse.data.price
        total.value = unit.value?.let { price.value?.times(Integer.valueOf(it)) }

    }

    public fun changeInstruction(ins: String) {
        instruction.value = ins
    }

    public fun placeOrder() {
        val order = NetworkOrder(
            "sam",
            instruction.value!!,
            asset.value!!,
            unit.value!!.toInt(),
            price.value!!,
            total.value!!,
            LocalDateTime.now(),
            LocalDateTime.now(),
            0
        )

        Log.d("TransactViewModel", order.toString())

        viewModelScope.launch {
            EzTradeWs.orderService.createOrder(order)
        }

        resetOrder()
    }

    private fun resetOrder() {
        asset.value = ""
        unit.value = ""
        price.value = 0.0
        total.value = 0.0
    }
}