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

@Dao
interface HoldingDao {
    @Query("select * from databaseholding")
    fun getHoldings(): LiveData<List<DatabaseHolding>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg holdings: DatabaseHolding)
}

@Database(entities = [DatabaseOrder::class, DatabaseHolding::class], version = 1)
abstract class FrontendDatabase : RoomDatabase() {
    abstract val orderDao: OrderDao
    abstract val holdingDao: HoldingDao
}

private lateinit var INSTANCE: FrontendDatabase

fun getDatabase(context: Context): FrontendDatabase {
    synchronized(FrontendDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                FrontendDatabase::class.java,
                "order"
            ).build()
        }
    }
    return INSTANCE
}