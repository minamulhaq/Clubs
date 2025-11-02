package com.miuh.clubs.presentation.screens.home_screen

import com.miuh.clubs.core.data.GameType

data class GenFilterButton(
    val title: String, val index: Int, val filter: GameType
)


val genFilterButtons: List<GenFilterButton> = listOf(
    GenFilterButton(
        title = "Current Gen", index = 0, filter = GameType.GEN5
    ),

    GenFilterButton(
        title = "Last Gen", index = 1, filter = GameType.GEN4
    ), GenFilterButton(
        title = "Switch", index = 2, filter = GameType.NX
    )
)

