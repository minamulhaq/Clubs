package com.miuh.clubs.domain.uc.networking_uc

import com.miuh.clubs.core.data.GenType
import com.miuh.clubs.core.data.LeaderboardType
import com.miuh.clubs.core.data.schema.ClubSchemaTop100
import com.miuh.clubs.domain.ClubsRepository

class GetTop100ClubsUseCase(
    private val repository: ClubsRepository
) : NetworkingUseCase<GenType, LeaderboardType, String?, List<ClubSchemaTop100>> {

    override suspend fun invoke(
        p: GenType?, q: LeaderboardType?, r: String?
    ): List<ClubSchemaTop100> {
        return repository.getTop100(p!!, q!!, r)
    }
}