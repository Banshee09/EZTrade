package com.eztrade.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.eztrade.database.OrdersDatabase
import com.eztrade.database.asDomainModel
import com.eztrade.domain.Order
import com.eztrade.network.EzTradeWs
import com.eztrade.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OrdersRepository(private val database: OrdersDatabase) {

    val orders: LiveData<List<Order>> = Transformations.map(database.orderDao.getOrders()) {
        it.map { it.asDomainModel() }
    }

    suspend fun refreshOrders() {
        withContext(Dispatchers.IO) {
//            //only use in testing
//            database.clearAllTables()
//            val orders = EzTradeWs.orderService.getOrders("sam")
//            database.orderDao.insertAll(*orders.map { it.asDatabaseModel() }.toTypedArray())
        }
    }

}