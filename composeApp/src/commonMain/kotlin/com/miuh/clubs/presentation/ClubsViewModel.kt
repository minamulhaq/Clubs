package com.miuh.clubs.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miuh.clubs.core.data.GenType
import com.miuh.clubs.core.data.LeaderboardType
import com.miuh.clubs.core.data.schema.ClubSchemaTop100
import com.miuh.clubs.domain.uc.networking_uc.NetworkingUseCase
import com.miuh.clubs.presentation.screens.home_screen.HomeScreenEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClubsViewModel(
    private val top100uc: NetworkingUseCase<GenType, LeaderboardType, List<ClubSchemaTop100>>
) : ViewModel() {

    private val _currentlySelectedGen = mutableStateOf(GenType.GEN5)
    private val _currentlySelectedLeaderBoard = mutableStateOf(LeaderboardType.ALL_TIME)
    private val _clubs = MutableStateFlow(emptyList<ClubSchemaTop100>())
    val clubs = _clubs.asStateFlow()

    init {
        getClubs()
    }

    private fun getClubs() {
        viewModelScope.launch {
            _clubs.value = emptyList()
            _clubs.value = top100uc(genType = _currentlySelectedGen.value, leaderboardType = _currentlySelectedLeaderBoard.value )
        }
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.GetTop100ClubsListEvent -> {
                _currentlySelectedGen.value = event.genType
                _currentlySelectedLeaderBoard.value = event.leaderboardType
                getClubs()
            }

            is HomeScreenEvent.SearchClubByNameEvent -> {
                println("Searching for club $event.")
            }
        }

    }
}
