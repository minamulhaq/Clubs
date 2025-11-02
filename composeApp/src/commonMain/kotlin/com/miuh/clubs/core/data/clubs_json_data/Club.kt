package com.miuh.clubs.core.data.clubs_json_data

import kotlinx.serialization.Serializable

@Serializable
data class Club(
    val bestDivision: String,
    val bestFinishGroup: String,
    val cleanSheets: String,
    val clubId: String,
    val clubInfo: ClubInfo,
    val clubName: String,
    val currentDivision: String,
    val gamesPlayed: String,
    val gamesPlayedPlayoff: String,
    val goals: String,
    val goalsAgainst: String,
    val goalsAgainstPerGame: String,
    val goalsPerGame: String,
    val losses: String,
    val platform: String,
    val rank: Int,
    val reputationlevel: String,
    val reputationtier: String,
    val skillRating: String,
    val ties: String,
    val wins: String
)