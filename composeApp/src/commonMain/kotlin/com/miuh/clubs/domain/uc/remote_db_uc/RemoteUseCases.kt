package com.miuh.clubs.domain.uc.remote_db_uc

import com.miuh.clubs.core.data.GenType
import com.miuh.clubs.core.data.LeaderboardType
import com.miuh.clubs.core.data.schema.ClubSchemaSearchByName
import com.miuh.clubs.core.data.schema.ClubSchemaTop100

interface RemoteUseCases {

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


    suspend fun getClubCrestByID(id: String?): String

}
