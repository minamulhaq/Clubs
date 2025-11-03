package com.miuh.clubs.domain.uc.networking_uc

import com.miuh.clubs.core.data.GameType
import com.miuh.clubs.core.data.schema.ClubSchemaTop100
import com.miuh.clubs.domain.ClubsRepository

class GetTop100ClubsUseCase(
    private val repository: ClubsRepository
) : NetworkingUseCase<GameType, List<ClubSchemaTop100>> {
    override suspend fun invoke(param: GameType): List<ClubSchemaTop100> {
        return repository.getTop100(param)
    }
}