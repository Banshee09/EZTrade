package com.eztrade.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Holding(
    var user: String,
    var asset: String,
    var unit: Int,
    var holdingId: Long,
    var price: Double
) : Parcelable