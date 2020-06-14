package com.eztrade.network

import java.time.LocalDateTime

data class NetworkPrice(
    var price: Double
)

data class NetworkStatus(
    var code: Int,
    var message: String
)

data class NetworkPriceResponse(
    var status: NetworkStatus,
    var data: NetworkPrice
)