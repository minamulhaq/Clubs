package com.miuh.clubs.domain

import com.miuh.clubs.core.data.GenType
import com.miuh.clubs.core.data.LeaderboardType
import com.miuh.clubs.core.data.schema.ClubSchemaSearchByName
import com.miuh.clubs.core.data.schema.ClubSchemaTop100


interface ClubsRepository {
    suspend fun getTop100(
        genType: GenType,
        leaderboardType: LeaderboardType,
        clubName: String?
    ): List<ClubSchemaTop100>

    suspend fun searchClubByName(
        genType: GenType,
        leaderboardType: LeaderboardType,
        name: String?
    ): List<ClubSchemaSearchByName>


    suspend fun getClubCrestById(
        crestID: String
    ): String

}