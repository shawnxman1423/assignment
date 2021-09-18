package io.liba.assignment.superdoMarket

import io.liba.assignment.superdoMarket.local.GroceriesDao
import io.liba.assignment.superdoMarket.models.GroceryFilter
import io.liba.assignment.superdoMarket.models.GroceryItem
import io.liba.assignment.superdoMarket.remote.GroceriesWebSocket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalSerializationApi::class)
@Singleton
class ObserveMarketUseCase @Inject constructor(
    private val webSocket: GroceriesWebSocket,
    private val groceriesDao: GroceriesDao,
) {
    val isObservingMarket = webSocket.isWebSocketActive
    val groceriesFlow = groceriesDao.get()

    private val json = Json { ignoreUnknownKeys = true }

    fun observe(filters: List<GroceryFilter>) = webSocket.messagesSharedFlow
        .map { message -> json.decodeFromString<GroceryItem>(message) }
        .onEach { item -> Timber.i("Grocery Item Received: $item") }
        .filter { item -> !filters.any { it.shouldFilter(item) } }
        .onEach { item -> Timber.i("Grocery Item Added: $item") }
        .onEach { item -> groceriesDao.insert(item) }
}
