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

internal class LocalDateTimeSerializer : JsonSerializer<LocalDateTime> {
    @Throws(JsonParseException::class)
    override fun serialize(
        src: LocalDateTime?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src?.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}

private val gson = GsonBuilder()
    .setPrettyPrinting()
    .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeDeserializer())
    .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeSerializer())
    .create()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()


interface OrderService {
    @GET("/orders")
    suspend fun getOrders(@Query("user") user: String): List<NetworkOrder>

    @POST("/orders")
    suspend fun createOrder(@Body order: NetworkOrder): NetworkOrder
}


object EzTradeWs {
    val orderService: OrderService by lazy {
        retrofit.create(OrderService::class.java)
    }

    val holdingService: HoldingService by lazy {
        retrofit.create(HoldingService::class.java)
    }
}





