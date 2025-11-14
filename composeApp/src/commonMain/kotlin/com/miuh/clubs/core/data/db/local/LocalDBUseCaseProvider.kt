package com.miuh.clubs.core.data.db.local

import com.miuh.clubs.core.data.GenType
import com.miuh.clubs.core.data.LeaderboardType
import com.miuh.clubs.core.data.schema.ClubSchemaSearchByName
import com.miuh.clubs.core.data.schema.ClubSchemaTop100
import com.miuh.clubs.domain.uc.networking_uc.DBUseCaseProvider
import com.miuh.clubs.domain.uc.networking_uc.NetworkingUseCase

class LocalDBUseCaseProvider(
    private val getTop100ClubsUseCase: NetworkingUseCase<GenType, LeaderboardType, String?, List<ClubSchemaTop100>>,
    private val searchClubUc: NetworkingUseCase<GenType, LeaderboardType, String, List<ClubSchemaSearchByName>>,
    private val getClubCrestAssetByIdUseCase: NetworkingUseCase<String, Unit?, Unit?, String>,
) : DBUseCaseProvider {
    override suspend fun getTop100(
        genType: GenType,
        leaderboardType: LeaderboardType,
        clubName: String?
    ): List<ClubSchemaTop100> {
        return getTop100ClubsUseCase(genType, leaderboardType, clubName)
    }

    override suspend fun searchClubByName(
        genType: GenType,
        leaderboardType: LeaderboardType,
        name: String?
    ): List<ClubSchemaSearchByName> {
        return searchClubUc(p = genType, q = leaderboardType, r = name)
    }

    override suspend fun getClubCrestByID(id: String?): String {
        return getClubCrestAssetByIdUseCase(p = id)

    }

}