package com.miuh.clubs.domain.uc.remote_db_uc


class GetClubCrestAssetByIdUseCase(
    private val repository: ClubsRemoteRepository
) : NetworkingUseCase<String, Unit?, Unit??, String> {
    override suspend fun invoke(p: String?, q: Unit?, r: Unit?): String {
        return repository.getClubCrestById(p!!)
    }
}
