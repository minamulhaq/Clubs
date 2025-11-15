package com.miuh.clubs.domain.uc.remote_db_uc

import com.miuh.clubs.core.data.GenType
import com.miuh.clubs.core.data.LeaderboardType
import com.miuh.clubs.core.data.schema.ClubSchemaSearchByName

class SearchClubByNameUseCase(
    private val repository: ClubsRemoteRepository
) : NetworkingUseCase<GenType, LeaderboardType, String?, List<ClubSchemaSearchByName>> {

    override suspend fun invoke(
        p: GenType?, q: LeaderboardType?, r: String?
    ): List<ClubSchemaSearchByName> {
        return repository.searchClubByName(p!!, q!!, r)
    }
}
