package com.miuh.clubs.domain.uc.local_db_uc

import com.miuh.clubs.core.data.domain.repositories.local.LocalDBRepository
import com.miuh.clubs.domain.repository.local.LocalRepository

class DeleteByIDUseCaseSuspend(
    private val localDBRepository: LocalRepository
) : LocalDBUseCaseSuspend<String, Unit> {
    override suspend fun invoke(params: String?) {
        return localDBRepository.deleteByClubId(params!!)
    }
}