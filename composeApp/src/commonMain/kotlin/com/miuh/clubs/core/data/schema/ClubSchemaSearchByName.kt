package com.miuh.clubs.core.data.schema

import kotlinx.serialization.Serializable

@Serializable
data class ClubSchemaSearchByName(
    val bestDivision: String,
    val cleanSheets: String,
    val clubId: String,
    val clubInfo: ClubInfo,
    val clubName: String,
    val currentDivision: String,
    val gamesPlayed: String,
    val gamesPlayedPlayoff: String,
    val goals: String,
    val goalsAgainst: String,
    val losses: String,
    val platform: String,
    val points: String,
    val promotions: String,
    val relegations: String,
    val reputationtier: String,
    val ties: String,
    val wins: String
)