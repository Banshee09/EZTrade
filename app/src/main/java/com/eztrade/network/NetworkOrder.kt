package com.eztrade.network

import com.eztrade.database.DatabaseOrder
import java.time.LocalDateTime

data class NetworkOrder(
    var user: String,
    var instruction: String,
    var asset: String,
    var unit: Int,
    var price: Double,
    var total: Double,
    var insertDate: LocalDateTime,
    var updateDate: LocalDateTime,
    var orderId: Long
)

fun NetworkOrder.asDatabaseModel(): DatabaseOrder {
    return DatabaseOrder(
        user = this.user,
        instruction = this.instruction,
        asset = this.asset,
        unit = this.unit,
        price = this.price,
        total = this.total,
        insertDate = this.insertDate,
        updateDate = this.updateDate,
        orderId = this.orderId
    )
}