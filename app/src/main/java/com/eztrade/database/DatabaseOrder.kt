package com.eztrade.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.eztrade.domain.Order
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


object LocalDateTimeConverters {

    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    @JvmStatic
    fun toLocalDateTime(value: String?): LocalDateTime? {
        return value?.let {
            return formatter.parse(value, LocalDateTime::from)
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromLocalDateTime(localDateTime: LocalDateTime?): String? {
        return localDateTime?.format(formatter)
    }
}

@Entity
@TypeConverters(LocalDateTimeConverters::class)
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

fun DatabaseOrder.asDomainModel(): Order {
    return Order(
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