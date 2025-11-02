package com.miuh.clubs.domain.uc.networking_uc

import com.miuh.clubs.core.data.clubs_json_data.Club
import com.miuh.clubs.domain.ClubsRepository

class GetTop100ClubsUseCase(
    private val repository: ClubsRepository
) : NetworkingUseCase<List<Club>> {
    override suspend fun invoke(): List<Club> {
        return repository.searchClubs()
    }
}