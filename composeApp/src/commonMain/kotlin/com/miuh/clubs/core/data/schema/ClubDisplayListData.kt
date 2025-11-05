package com.miuh.clubs.core.data.schema

import com.miuh.clubs.core.data.db.local.ClubEntity


data class ClubDisplayListData(
    val clubId: String,
    val clubName: String,
    val clubInfo: ClubInfo? = null,
    val skillRating: String = "-",
    val crestImageUrl: String = ""
)
fun ClubDisplayListData.toEntity(): ClubEntity {
    return ClubEntity(
        // id is omitted or explicitly set to 0, relying on @PrimaryKey(autoGenerate = true)
        clubId = this.clubId,
        clubName = this.clubName,
        skillRating = this.skillRating,
        crestImageUrl = this.crestImageUrl
    )
}


