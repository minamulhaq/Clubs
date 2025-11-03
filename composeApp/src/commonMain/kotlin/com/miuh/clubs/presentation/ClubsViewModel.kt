package com.miuh.clubs.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miuh.clubs.core.data.GameType
import com.miuh.clubs.core.data.schema.ClubSchemaTop100
import com.miuh.clubs.domain.uc.networking_uc.NetworkingUseCase
import com.miuh.clubs.presentation.screens.home_screen.HomeScreenEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClubsViewModel(
    private val top100uc: NetworkingUseCase<List<ClubSchemaTop100>>
) : ViewModel() {

    private val _clubSerachText = mutableStateOf("")

    private val _currentlySelectedGen = mutableStateOf(GameType.GEN5)
    private val _clubs = MutableStateFlow(emptyList<ClubSchemaTop100>())
    val clubs = _clubs.asStateFlow()

    init {
        getClubs()
    }

    private fun getClubs() {
        viewModelScope.launch {
            _clubs.value = emptyList()
            _clubs.value = top100uc()
        }
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.GetClubsListEvent -> {
                _currentlySelectedGen.value = event.gameType
                println("Currently selected gen : ${_currentlySelectedGen.value}")
            }

            is HomeScreenEvent.SearchClubByNameEvent -> {
                println("Searching for club ${event.clubName}")
            }
        }

    }
}
