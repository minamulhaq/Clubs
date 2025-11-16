package com.miuh.clubs.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.traceEventEnd
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.miuh.clubs.core.data.GenType
import com.miuh.clubs.core.data.LeaderboardType
import com.miuh.clubs.core.data.schema.ClubDisplayListData
import com.miuh.clubs.core.data.schema.toDisplayData
import com.miuh.clubs.core.data.schema.toEntity
import com.miuh.clubs.core.util.Result
import com.miuh.clubs.domain.uc.local_db_uc.LocalDbUseCases
import com.miuh.clubs.domain.uc.remote_db_uc.RemoteUseCases
import com.miuh.clubs.presentation.screens.home_screen.HomeScreenEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.math.log

class ClubsViewModel(
    private val localUseCases: LocalDbUseCases,
    private val remoteUseCases: RemoteUseCases
) : ViewModel() {
    private val logger = Logger.withTag(this::class.simpleName.toString())


    private val _currentlySelectedGen = mutableStateOf(GenType.GEN5)
    private val _currentlySelectedLeaderBoard = mutableStateOf(LeaderboardType.ALL_TIME)
    private val _clubs = MutableStateFlow(emptyList<ClubDisplayListData>())
    val clubs = _clubs.asStateFlow()

    //    private val clubsDao = clubsDb.clubsDao()

    val bookmarkedClubs = localUseCases.getAllClubs().map { clubEntityList ->
        clubEntityList.map {
            it.toClubListDisplayData()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        getClubs()
    }

    private fun getClubs() {
        viewModelScope.launch(Dispatchers.IO) {
            _clubs.value = emptyList()
            _clubs.value = remoteUseCases.getTop100(
                genType = _currentlySelectedGen.value,
                leaderboardType = _currentlySelectedLeaderBoard.value,
                clubName = null
            ).map { club ->
                club.toDisplayData().copy(
                    crestImageUrl = remoteUseCases.getClubCrestByID(club.clubInfo.customKit.crestAssetId)
                )
            }
        }
    }

    private fun searchClubByName(clubName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _clubs.value = emptyList()
            _clubs.value = remoteUseCases.searchClubByName(
                genType = _currentlySelectedGen.value,
                leaderboardType = _currentlySelectedLeaderBoard.value,
                name = clubName.trim()
            ).map { club ->
                club.toDisplayData().copy(
                    crestImageUrl = remoteUseCases.getClubCrestByID(club.clubInfo.customKit.crestAssetId)
                )
            }
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
                searchClubByName(event.clubName)
            }

            is HomeScreenEvent.AddClubToBookmarksClubEvent -> {
                addClubToBookmarks(event.club)

            }

            is HomeScreenEvent.RemoveClubFromBookmarksClubEvent -> {
                removeClubFromBookmarks(
                    event.club
                )
            }

            is HomeScreenEvent.ToClubDetailsScreenClubEvent -> {
                viewModelScope.launch {
                    val stats = remoteUseCases.getClubOverallStats(
                        genType = GenType.GEN5,
                        id = event.club.clubId.toInt()
                    )
                    when (stats){
                        is Result.Error<*> -> {
                            logger.e { stats.error.toString()}
                        }
                        is Result.Success<*> -> {
                            logger.d {
                                "Stats: ${stats.data}"
                            }
                        }
                    }

                }

            }
        }

    }

    private fun removeClubFromBookmarks(club: ClubDisplayListData) {
        viewModelScope.launch {
            logger.d {
                "Deleting $club"
            }
            localUseCases.deleteClubByID(club.clubId)
        }
    }

    private fun addClubToBookmarks(club: ClubDisplayListData) {
        viewModelScope.launch {
            localUseCases.insertAll(listOf(club.toEntity()))
        }
    }
}
