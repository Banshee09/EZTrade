package com.eztrade.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class DatabaseOrder(
    @PrimaryKey
    var orderId: Long,
    var user: String,
    var asset: String,
    var quantity: Int,
    var price: Double,
    var total: Double,
    var insertDate: LocalDateTime,
    var updateDate: LocalDateTime
)