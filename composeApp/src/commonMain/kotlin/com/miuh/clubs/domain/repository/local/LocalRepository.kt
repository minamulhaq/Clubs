package com.miuh.clubs.domain.repository.local

import com.miuh.clubs.core.data.db.local.ClubEntity
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun getAll():
            Flow<List<ClubEntity>>

    suspend fun deleteByClubId(clubId: String)
    suspend fun insertAll(clubs: List<ClubEntity>)
}
