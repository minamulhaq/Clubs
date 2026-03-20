package com.miuh.clubs.core.data.domain.uc.local

import com.miuh.clubs.core.data.db.local.ClubEntity
import com.miuh.clubs.domain.uc.local_db_uc.LocalDbUseCases
import kotlinx.coroutines.flow.Flow

class ClubsLocalDBUseCases(
    private val getAllFromDatabaseUseCase: GetAllFromDatabaseUseCase,
    private val deleteByIDUseCase: DeleteByIDUseCaseSuspend,
    private val insertClubsInDBUseCase: InsertClubsInDBUseCaseSuspend
) : LocalDbUseCases {

    override fun getAllClubs(): Flow<List<ClubEntity>> {
        return getAllFromDatabaseUseCase()
    }

    override suspend fun deleteClubByID(id: String) {
        return deleteByIDUseCase(params = id)
    }

    override suspend fun insertAll(clubs: List<ClubEntity>) {
        return insertClubsInDBUseCase(params = clubs)
    }
}