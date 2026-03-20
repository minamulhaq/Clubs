package com.miuh.clubs.domain.uc.local_db_uc

import com.miuh.clubs.core.data.db.local.ClubEntity
import kotlinx.coroutines.flow.Flow

interface LocalDbUseCases {
    fun getAllClubs(): Flow<List<ClubEntity>>
    suspend fun deleteClubByID(id: String)

    suspend fun insertAll(clubs: List<ClubEntity>)

}