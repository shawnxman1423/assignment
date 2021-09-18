package io.liba.assignment.superdoMarket

import io.liba.assignment.superdoMarket.remote.GroceriesWebSocket
import javax.inject.Inject

class StartObservingMarketUseCase @Inject constructor(
    private val groceriesWebSocket: GroceriesWebSocket
) {
    fun start() {
        groceriesWebSocket.start()
    }
}
