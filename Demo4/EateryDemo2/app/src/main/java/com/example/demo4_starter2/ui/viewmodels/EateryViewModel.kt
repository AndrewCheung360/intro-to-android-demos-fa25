package com.example.demo4_starter2.eatery

import androidx.lifecycle.ViewModel
import com.example.demo4_starter2.data.model.Eatery
import com.example.demo4_starter2.data.model.Location
import com.example.demo4_starter2.data.repository.EateryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlin.collections.filter

@HiltViewModel
class EateryViewModel @Inject constructor(
    private val eateryRepository: EateryRepository
) : ViewModel() {
    data class EateryUIState(
        val northSelected: Boolean = false,
        val centralSelected: Boolean = false,
        val westSelected: Boolean = false,
        val nowOpen: Boolean = false,
        val allEateries: List<Eatery> = emptyList()
    ) {
        val filteredEateries: List<Eatery>
            get() = allEateries.filter { eatery ->
                //TODO: implement the nowOpen filter-logic here!
                ((northSelected && eatery.location == Location.NORTH) ||
                        (centralSelected && eatery.location == Location.CENTRAL) ||
                        (westSelected && eatery.location == Location.WEST) ||
                        (!northSelected && !centralSelected && !westSelected)) &&
                        (!nowOpen || eatery.isOpen)
            }
    }

    private val _uiState = MutableStateFlow(EateryUIState())
    val uiState: StateFlow<EateryUIState> = _uiState.asStateFlow()

    fun onLocationSelected(location: Location) {
        _uiState.value = when (location) {
            Location.NORTH -> _uiState.value.copy(
                northSelected = !_uiState.value.northSelected
            )

            Location.CENTRAL -> _uiState.value.copy(
                centralSelected = !_uiState.value.centralSelected
            )

            Location.WEST -> _uiState.value.copy(
                westSelected = !_uiState.value.westSelected
            )
        }
    }

    //TODO: implement the nowOpen filter toggle function
    fun onNowOpenSelected() {
        _uiState.value = _uiState.value.copy(
            nowOpen = !_uiState.value.nowOpen
        )
    }

    init {
        loadEateries()
    }

    private fun loadEateries() {
        _uiState.value = _uiState.value.copy(
            allEateries = eateryRepository.getAllEateries()
        )
    }
}
