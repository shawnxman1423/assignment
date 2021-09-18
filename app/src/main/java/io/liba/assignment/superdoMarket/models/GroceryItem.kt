package io.liba.assignment.superdoMarket.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "groceries")
data class GroceryItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val weight: String,
    val bagColor: String,
)
