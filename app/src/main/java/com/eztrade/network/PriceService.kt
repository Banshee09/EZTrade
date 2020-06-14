package com.eztrade.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDateTime

private const val BASE_URL = "https://api.stockdio.com/data/financial/prices/v1/"
private const val APP_KEY = "3D38B8238ACB43749DD683FE6768A909"
private const val STOCK_EXCHANGE = "ASX"

private val gson = GsonBuilder()
    .setPrettyPrinting()
    .create()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()


interface PriceService {
    @GET("getlatestprice")
    suspend fun getLatestPrice(
        @Query("symbol") symbol: String,
        @Query("stockExchange") stockExchange: String = STOCK_EXCHANGE,
        @Query("app-key") appKey: String = APP_KEY
    ): NetworkPriceResponse
}


object PriceWs {
    val priceService: PriceService by lazy {
        retrofit.create(PriceService::class.java)
    }
}