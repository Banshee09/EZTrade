package com.eztrade.network

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


private const val BASE_URL = "http://10.0.2.2:8080"

internal class LocalDateTimeDeserializer : JsonDeserializer<LocalDateTime> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): LocalDateTime {
        return LocalDateTime.parse(
            json.asString,
            DateTimeFormatter.ISO_LOCAL_DATE_TIME
        )
    }
}

private val gson = GsonBuilder()
    .setPrettyPrinting()
    .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeDeserializer())
    .create()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()


interface OrderService {
    @GET("/orders")
    suspend fun getOrders(@Query("user") user: String): List<NetworkOrder>
}


object EzTradeWs {
    val orderService: OrderService by lazy {
        retrofit.create(OrderService::class.java)
    }
}





