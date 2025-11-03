package com.miuh.clubs.core.data.schema

data class ClubDisplayListData(
    val clubId: String,
    val clubName: String,
    val clubInfo: ClubInfo,
    val skillRating: String = "-",
    val crestImageUrl: String = ""
)
