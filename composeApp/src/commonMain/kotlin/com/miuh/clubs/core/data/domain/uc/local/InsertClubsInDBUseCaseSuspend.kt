package com.miuh.clubs.core.data.domain.uc.local

import com.miuh.clubs.core.data.db.local.ClubEntity
import com.miuh.clubs.domain.repository.local.LocalRepository
import com.miuh.clubs.domain.uc.local_db_uc.LocalDBUseCaseSuspend

class InsertClubsInDBUseCaseSuspend(
    private val localDBRepository: LocalRepository
) : LocalDBUseCaseSuspend<List<ClubEntity>, Unit> {
    override suspend fun invoke(params: List<ClubEntity>?) {
        params?.let {
            return localDBRepository.insertAll(clubs = params)
        }
    }
}