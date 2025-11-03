package com.miuh.clubs.presentation.screens.home_screen

import com.miuh.clubs.core.data.GameType

sealed interface HomeScreenEvent {
    class GetClubsListEvent(val gameType: GameType) : HomeScreenEvent
    class SearchClubByNameEvent(val clubName: String) : HomeScreenEvent
}