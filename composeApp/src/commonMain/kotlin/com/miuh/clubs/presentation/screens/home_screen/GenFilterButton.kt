package com.miuh.clubs.presentation.screens.home_screen

import com.miuh.clubs.core.data.GenType

data class GenFilterButton(
    val title: String, val index: Int, val genType: GenType
)


val genFilterButtons: List<GenFilterButton> = listOf(
    GenFilterButton(
        title = "Current Gen", index = 0, genType = GenType.GEN5
    ),

    GenFilterButton(
        title = "Last Gen", index = 1, genType = GenType.GEN4
    ), GenFilterButton(
        title = "Switch", index = 2, genType = GenType.NX
    )
)

