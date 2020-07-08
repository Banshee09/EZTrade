package com.eztrade.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.eztrade.database.FrontendDatabase
import com.eztrade.database.asDomainModel
import com.eztrade.domain.Holding
import com.eztrade.domain.Order
import com.eztrade.network.EzTradeWs
import com.eztrade.network.PriceWs
import com.eztrade.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HoldingRepository(private val database: FrontendDatabase) {

    val holdings: LiveData<List<Holding>> = Transformations.map(database.holdingDao.getHoldings()) {
        it.map { it.asDomainModel() }
    }

    suspend fun refreshHoldings() {
        withContext(Dispatchers.IO) {
            //only use in testing
//            database.clearAllTables()
            val holdings = EzTradeWs.holdingService.getHoldings("sam")

            database.holdingDao.insertAll(*holdings.map { it ->
                it.asDatabaseModel().apply {
                    //price = PriceWs.priceService.getLatestPrice(it.asset).data.price
                }
            }.toTypedArray())
        }
    }

}