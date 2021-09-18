package io.liba.assignment.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.liba.assignment.superdoMarket.models.GroceryItem
import io.liba.assignment.superdoMarket.local.GroceriesDao

@Database(
    version = 1,
    exportSchema = false,
    entities = [GroceryItem::class],
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun groceriesDao(): GroceriesDao
}
