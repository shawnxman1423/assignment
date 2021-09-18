package io.liba.assignment.superdoMarket.models

interface GroceryFilter {
    fun shouldFilter(item: GroceryItem): Boolean
}

data class WeightGroceryFilter(private val weight: Double) : GroceryFilter {
    override fun shouldFilter(item: GroceryItem): Boolean {
        return item.weight.removeSuffix("kg").toDouble() <= weight
    }
}
