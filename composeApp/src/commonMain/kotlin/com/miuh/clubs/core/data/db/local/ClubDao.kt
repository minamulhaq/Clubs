package com.miuh.clubs.core.data.db.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ClubDao {
    @Query("SELECT * FROM bookmarked_clubs")
    fun getAll(): Flow<List<ClubEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(clubs: List<ClubEntity>)

    @Upsert
    suspend fun upsert(club: ClubEntity)

    @Delete
    suspend fun delete(club: ClubEntity)

    @Query("DELETE FROM bookmarked_clubs WHERE clubId = :clubId")
    suspend fun deleteByClubId(clubId: String)
}