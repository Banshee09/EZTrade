package com.eztrade.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface OrderDao {
    @Query("select * from databaseorder")
    fun getOrders(): LiveData<List<DatabaseOrder>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg orders: DatabaseOrder)
}

@Database(entities = [DatabaseOrder::class], version = 1)
abstract class OrdersDatabase : RoomDatabase() {
    abstract val orderDao: OrderDao
}

private lateinit var INSTANCE: OrdersDatabase

fun getDatabase(context: Context): OrdersDatabase {
    synchronized(OrdersDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                OrdersDatabase::class.java,
                "order"
            ).build()
        }
    }
    return INSTANCE
}