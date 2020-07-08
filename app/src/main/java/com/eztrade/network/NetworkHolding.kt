package com.eztrade.network

import com.eztrade.database.DatabaseHolding
import com.eztrade.database.DatabaseOrder
import java.time.LocalDateTime

data class NetworkHolding(
    var user: String,
    var asset: String,
    var unit: Int,
    var holdingId: Long
)

fun NetworkHolding.asDatabaseModel(): DatabaseHolding {
    return DatabaseHolding(
        user = this.user,
        asset = this.asset,
        unit = this.unit,
        holdingId = this.holdingId,
        price = 0.0
    )
}