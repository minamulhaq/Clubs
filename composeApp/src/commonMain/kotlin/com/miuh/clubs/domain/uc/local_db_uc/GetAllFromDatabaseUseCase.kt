package com.miuh.clubs.domain.uc.local_db_uc

import com.miuh.clubs.core.data.db.local.ClubEntity
import com.miuh.clubs.core.data.domain.repositories.local.LocalDBRepository
import com.miuh.clubs.domain.repository.local.LocalRepository
import kotlinx.coroutines.flow.Flow

class GetAllFromDatabaseUseCase(
    private val localDBRepository: LocalRepository
) : LocalDBUseCaseFlow<Any, Flow<List<ClubEntity>>> {
    override fun invoke(params: Any?): Flow<List<ClubEntity>> {
        return localDBRepository.getAll()
    }
}