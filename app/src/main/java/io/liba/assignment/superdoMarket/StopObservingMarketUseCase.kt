package io.liba.assignment.superdoMarket

import io.liba.assignment.superdoMarket.remote.GroceriesWebSocket
import javax.inject.Inject

class StopObservingMarketUseCase @Inject constructor(
    private val groceriesWebSocket: GroceriesWebSocket
) {
    fun stop() {
        groceriesWebSocket.stop()
    }
}
