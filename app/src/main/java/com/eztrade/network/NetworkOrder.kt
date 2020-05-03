package com.eztrade.network

import com.eztrade.database.DatabaseOrder
import java.time.LocalDateTime

data class NetworkOrder(
    var user: String,
    var asset: String,
    var quantity: Int,
    var price: Double,
    var total: Double,
    var insertDate: LocalDateTime,
    var updateDate: LocalDateTime,
    var orderId: Long
)

fun NetworkOrder.asDatabaseModel(): DatabaseOrder {
    return DatabaseOrder(
        user = this.user,
        asset = this.asset,
        quantity = this.quantity,
        price = this.price,
        total = this.total,
        insertDate = this.insertDate,
        updateDate = this.updateDate,
        orderId = this.orderId
    )
}