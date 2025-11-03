package com.miuh.clubs.core.data.schema

import kotlinx.serialization.Serializable

@Serializable
data class ClubInfo(
    val clubId: Int,
    val customKit: CustomKit,
    val name: String,
    val regionId: Int,
    val teamId: Int
)