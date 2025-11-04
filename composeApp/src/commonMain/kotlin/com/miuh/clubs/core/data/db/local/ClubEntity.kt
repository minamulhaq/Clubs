package com.miuh.clubs.core.data.db.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_clubs")
data class ClubEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val clubId: String,
    val clubName: String,
    val skillRating: String = "",
    val crestImageUrl: String = ""
)