package com.miuh.clubs.domain.uc.networking_uc

import coil3.ImageLoader
import com.miuh.clubs.domain.ClubsRepository


class GetClubCrestAssetByIdUseCase(
    private val repository: ClubsRepository
) : NetworkingUseCase<String, Unit?, Unit??, String> {
    override suspend fun invoke(p: String?, q: Unit?, r: Unit?): String {
        return repository.getClubCrestById(p!!)
    }
}
