package com.miuh.clubs.domain.uc.networking_uc

import com.miuh.clubs.core.data.GenType
import com.miuh.clubs.core.data.LeaderboardType
import com.miuh.clubs.core.data.schema.ClubSchemaSearchByName
import com.miuh.clubs.core.data.schema.ClubSchemaTop100
import com.miuh.clubs.domain.ClubsRepository

class SearchClubByNameUseCase(
    private val repository: ClubsRepository
) : NetworkingUseCase<GenType, LeaderboardType, String?, List<ClubSchemaSearchByName>> {

    override suspend fun invoke(
        p: GenType?, q: LeaderboardType?, r: String?
    ): List<ClubSchemaSearchByName> {
        return repository.searchClubByName(p!!, q!!, r)
    }
}
