package com.eztrade.network

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