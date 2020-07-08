package com.eztrade.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.eztrade.domain.Holding
import com.eztrade.domain.Order
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Entity
data class DatabaseHolding(
    @PrimaryKey
    var holdingId: Long,
    var user: String,
    var asset: String,
    var unit: Int,
    var price: Double
)

fun DatabaseHolding.asDomainModel(): Holding {
    return Holding(
        user = this.user,
        asset = this.asset,
        unit = this.unit,
        holdingId = this.holdingId,
        price = this.price
    )
}