package io.liba.assignment.screens.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber

data class HomeState(
    val counter: Int = 0,
    val isInitialized: Boolean,
)

private val homeViewModelHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    Timber.e(throwable)
}

class HomeViewModel(
    handle: SavedStateHandle,
) : ViewModel() {

    private val mutableCounter = handle.getLiveData("counter", 0)

    val viewState = combine(
        mutableCounter.asFlow(),
        flowOf(Unit)
    ) { counter, _ ->
        HomeState(
            counter = counter,
            isInitialized = true
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        HomeState(isInitialized = false)
    )

    init {
        val a = 5
    }

    fun addToCounter() = viewModelScope.launch(homeViewModelHandler) {
        mutableCounter.value = mutableCounter.value?.plus(1)
    }
}
