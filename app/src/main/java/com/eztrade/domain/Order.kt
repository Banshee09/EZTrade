package com.eztrade.domain

import java.time.LocalDateTime

data class Order(
    var user: String,
    var asset: String,
    var quantity: Int,
    var price: Double,
    var total: Double,
    var insertDate: LocalDateTime,
    var updateDate: LocalDateTime,
    var orderId: Long
)