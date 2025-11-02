package com.miuh.clubs.domain.uc.networking_uc

import com.miuh.clubs.domain.ClubsRepository

class GetTop100ClubsUseCase(
    private val repository: ClubsRepository
) : NetworkingUseCase<List<String>> {
    override suspend fun invoke(): List<String> {
        return repository.searchClubs()
    }
}