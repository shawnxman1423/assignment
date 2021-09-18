package io.liba.assignment.superdoMarket.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.liba.assignment.superdoMarket.models.GroceryItem
import kotlinx.coroutines.flow.Flow

@Dao
abstract class GroceriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(item: GroceryItem)

    @Query("SELECT * FROM groceries ORDER BY id DESC")
    abstract fun get(): Flow<List<GroceryItem>>
}
