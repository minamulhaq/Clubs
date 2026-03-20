package com.miuh.clubs.core.data.domain.repositories.local

import com.miuh.clubs.core.data.db.local.ClubDao
import com.miuh.clubs.core.data.db.local.ClubEntity
import com.miuh.clubs.domain.repository.local.LocalRepository
import kotlinx.coroutines.flow.Flow

class LocalDBRepository(
    private val dao: ClubDao

) : LocalRepository {
    override fun getAll(): Flow<List<ClubEntity>> {
        return dao.getAll()
    }

    override suspend fun deleteByClubId(clubId: String) {
        return dao.deleteByClubId(clubId = clubId)
    }

    override suspend fun insertAll(clubs: List<ClubEntity>) {
        return dao.insertAll(clubs = clubs)
    }


}