package com.eztrade.network

import com.google.gson.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


private const val BASE_URL = "http://10.0.2.2:8080"


private val gson = GsonBuilder()
    .setPrettyPrinting()
    .create()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()


interface HoldingService {
    @GET("/holdings")
    suspend fun getHoldings(@Query("user") user: String): List<NetworkHolding>
}








