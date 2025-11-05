package com.miuh.clubs.core.data.db.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.miuh.clubs.core.data.schema.ClubDisplayListData
import kotlin.String

@Entity(tableName = "bookmarked_clubs")
data class ClubEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val clubId: String,
    val clubName: String,
    val skillRating: String = "",
    val crestImageUrl: String = ""
) {
    fun toClubListDisplayData(): ClubDisplayListData {
        return ClubDisplayListData(
            clubName = this.clubName,
            clubId = this.clubId,
            skillRating = this.skillRating,
            crestImageUrl = this.crestImageUrl
        )
    }
}