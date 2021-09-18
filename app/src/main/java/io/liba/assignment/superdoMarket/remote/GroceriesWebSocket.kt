package io.liba.assignment.superdoMarket.remote

import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import io.liba.assignment.common.defaultCoroutineExceptionHandler
import io.liba.assignment.superdoMarket.models.GroceryItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GroceriesWebSocket @Inject constructor(
    private val client: HttpClient,
) : CoroutineScope by CoroutineScope(Dispatchers.Default) {

    private var websocketJob: Job? = null

    private val mutableIsWebSocketActive = MutableStateFlow(false)
    val isWebSocketActive: Flow<Boolean> = mutableIsWebSocketActive

    private val mutableMessagesFlow = MutableSharedFlow<String>()
    val messagesSharedFlow = mutableMessagesFlow.asSharedFlow()

    @OptIn(ExperimentalSerializationApi::class)
    fun start() {
        mutableIsWebSocketActive.value = true
        websocketJob = launch(defaultCoroutineExceptionHandler) {
            client.webSocket(
                method = HttpMethod.Get,
                host = "superdo-groceries.herokuapp.com",
                port = 0,
                path = "/receive") {
                while (true) {
                    val othersMessage = incoming.receive() as? Frame.Text
                    val messageText = othersMessage?.readText() ?: break
                    mutableMessagesFlow.emit(messageText)
                    Timber.d("WS: $messageText")
                }
            }
            mutableIsWebSocketActive.value = false
        }
    }

    fun stop() {
        mutableIsWebSocketActive.value = false
        websocketJob?.cancel()
        websocketJob = null
    }
}
