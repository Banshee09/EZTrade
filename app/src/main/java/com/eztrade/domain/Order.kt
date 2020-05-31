package com.eztrade.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Order(
    var user: String,
    var asset: String,
    var quantity: Int,
    var price: Double,
    var total: Double,
    var insertDate: LocalDateTime,
    var updateDate: LocalDateTime,
    var orderId: Long
) : Parcelable