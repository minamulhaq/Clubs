package com.miuh.clubs.presentation.screens.home_screen

import com.miuh.clubs.core.data.GenType
import com.miuh.clubs.core.data.LeaderboardType

sealed interface HomeScreenEvent {
    class GetTop100ClubsListEvent(val genType: GenType, val leaderboardType: LeaderboardType) : HomeScreenEvent
    class SearchClubByNameEvent(val clubName: String) : HomeScreenEvent
}