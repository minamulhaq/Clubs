package com.miuh.clubs.core.data.db.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor


@Suppress("KotlinNoActualForExpect")
expect object ClubsDatabaseConstructor : RoomDatabaseConstructor<ClubsDatabase> {
    override fun initialize(): ClubsDatabase
}

@Database(
    entities = [ClubEntity::class], version = 1, exportSchema = true,
)
@ConstructedBy(ClubsDatabaseConstructor::class)
abstract class ClubsDatabase : RoomDatabase() {

    abstract fun clubsDao(): ClubDao
}

