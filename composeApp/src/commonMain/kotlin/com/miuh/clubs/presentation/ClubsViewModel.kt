package com.miuh.clubs.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import coil3.ImageLoader
import com.miuh.clubs.core.data.GenType
import com.miuh.clubs.core.data.LeaderboardType
import com.miuh.clubs.core.data.db.local.ClubsDatabase
import com.miuh.clubs.core.data.schema.ClubDisplayListData
import com.miuh.clubs.core.data.schema.ClubSchemaSearchByName
import com.miuh.clubs.core.data.schema.ClubSchemaTop100
import com.miuh.clubs.core.data.schema.toDisplayData
import com.miuh.clubs.core.data.schema.toEntity
import com.miuh.clubs.domain.uc.networking_uc.NetworkingUseCase
import com.miuh.clubs.domain.uc.networking_uc.DBUseCaseProvider
import com.miuh.clubs.presentation.screens.home_screen.HomeScreenEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ClubsViewModel(
    private val imageLoader: ImageLoader,
    private val clubsDb: ClubsDatabase,
    private val localDBUseCaseProvider: DBUseCaseProvider
) : ViewModel() {
    private val logger = Logger.withTag(ClubsViewModel::class.simpleName.toString())


    private val _currentlySelectedGen = mutableStateOf(GenType.GEN5)
    private val _currentlySelectedLeaderBoard = mutableStateOf(LeaderboardType.ALL_TIME)
    private val _clubs = MutableStateFlow(emptyList<ClubDisplayListData>())
    val clubs = _clubs.asStateFlow()

    private val clubsDao = clubsDb.clubsDao()
    val bookmarkedClubs = clubsDao.getAll().map { clubEntityList ->
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
            _clubs.value = localDBUseCaseProvider.getTop100(
                genType = _currentlySelectedGen.value,
                leaderboardType = _currentlySelectedLeaderBoard.value,
                clubName = null
            ).map { club ->
                club.toDisplayData().copy(
                    crestImageUrl = localDBUseCaseProvider.getClubCrestByID(club.clubInfo.customKit.crestAssetId)
                )
            }
        }
    }

    private fun searchClubByName(clubName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _clubs.value = emptyList()
            _clubs.value = localDBUseCaseProvider.searchClubByName(
                genType = _currentlySelectedGen.value,
                leaderboardType = _currentlySelectedLeaderBoard.value,
                name = clubName.trim()
            ).map { club ->
                club.toDisplayData().copy(
                    crestImageUrl = localDBUseCaseProvider.getClubCrestByID(club.clubInfo.customKit.crestAssetId)
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
        }

    }

    private fun removeClubFromBookmarks(club: ClubDisplayListData) {
        viewModelScope.launch {
            logger.d {
                "Deleting $club"
            }
            clubsDao.deleteByClubId(club.clubId)
        }
    }

    private fun addClubToBookmarks(club: ClubDisplayListData) {
        viewModelScope.launch {
            clubsDao.insertAll(listOf(club.toEntity()))
        }
    }
}
