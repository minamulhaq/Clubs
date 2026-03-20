package com.miuh.clubs.core.data.domain.uc.local

import com.miuh.clubs.domain.repository.local.LocalRepository
import com.miuh.clubs.domain.uc.local_db_uc.LocalDBUseCaseSuspend

class DeleteByIDUseCaseSuspend(
    private val localDBRepository: LocalRepository
) : LocalDBUseCaseSuspend<String, Unit> {
    override suspend fun invoke(params: String?) {
        return localDBRepository.deleteByClubId(params!!)
    }
}