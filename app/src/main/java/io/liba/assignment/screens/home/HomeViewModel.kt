package io.liba.assignment.screens.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.liba.assignment.common.defaultCoroutineExceptionHandler
import io.liba.assignment.superdoMarket.ObserveMarketUseCase
import io.liba.assignment.superdoMarket.StartObservingMarketUseCase
import io.liba.assignment.superdoMarket.StopObservingMarketUseCase
import io.liba.assignment.superdoMarket.models.GroceryFilter
import io.liba.assignment.superdoMarket.models.GroceryItem
import io.liba.assignment.superdoMarket.models.WeightGroceryFilter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeState(
    val groceries: List<GroceryItem> = emptyList(),
    val isActive: Boolean = false,
    val weightFilter: String = "",
    val isInitialized: Boolean,
)

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalStdlibApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    handle: SavedStateHandle,
    private val startObservingMarketUseCase: StartObservingMarketUseCase,
    private val stopObservingMarketUseCase: StopObservingMarketUseCase,
    observeMarketUseCase: ObserveMarketUseCase,
) : ViewModel() {

    private val mutableWeightFilter = handle.getLiveData("weight_filter", "")

    val viewState = combine(
        observeMarketUseCase.groceriesFlow,
        observeMarketUseCase.isObservingMarket,
        mutableWeightFilter.asFlow()
    ) { groceries, isActive, weightFilter ->
        HomeState(
            groceries = groceries,
            isActive = isActive,
            weightFilter = weightFilter,
            isInitialized = true
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        HomeState(isInitialized = false)
    )

    init {
        combine(
            mutableWeightFilter.asFlow(),
            flowOf(Unit) // Future Filter Stub
        ) { weightFilter, _ ->
            buildList<GroceryFilter> {
                when (weightFilter) {
                    "" -> Unit // Don't add Filter
                    else -> add(WeightGroceryFilter(weightFilter.toDouble()))
                }
            }
        }.flatMapLatest { filters -> observeMarketUseCase.observe(filters) }
            .launchIn(viewModelScope)
    }

    fun start() = viewModelScope.launch(defaultCoroutineExceptionHandler) {
        startObservingMarketUseCase.start()
    }

    fun stop() = viewModelScope.launch(defaultCoroutineExceptionHandler) {
        stopObservingMarketUseCase.stop()
    }

    fun setWeightFilter(weight: String) = viewModelScope.launch(defaultCoroutineExceptionHandler) {
        mutableWeightFilter.value = weight
    }
}
