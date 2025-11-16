package com.miuh.clubs.core.data.domain.uc.remote

import com.miuh.clubs.core.data.GenType
import com.miuh.clubs.core.data.LeaderboardType
import com.miuh.clubs.core.data.schema.ClubSchemaSearchByName
import com.miuh.clubs.domain.uc.remote_db_uc.ClubsRemoteRepository
import com.miuh.clubs.domain.uc.remote_db_uc.NetworkingUseCase

class SearchClubByNameUseCase(
    private val repository: ClubsRemoteRepository
) : NetworkingUseCase<GenType, LeaderboardType, String?, List<ClubSchemaSearchByName>> {

    override suspend fun invoke(
        p: GenType?, q: LeaderboardType?, r: String?
    ): List<ClubSchemaSearchByName> {
        return repository.searchClubByName(p!!, q!!, r)
    }
}
