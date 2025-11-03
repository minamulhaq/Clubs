package com.miuh.clubs.core.data.schema

import kotlinx.serialization.Serializable

@Serializable
data class CustomKit(
    val crestAssetId: String,
    val crestColor: String,
    val customAwayKitId: String,
    val customKeeperKitId: String,
    val customKitId: String,
    val customThirdKitId: String,
    val dCustomKit: String,
    val kitAColor1: String,
    val kitAColor2: String,
    val kitAColor3: String,
    val kitAColor4: String,
    val kitColor1: String,
    val kitColor2: String,
    val kitColor3: String,
    val kitColor4: String,
    val kitId: String,
    val kitThrdColor1: String,
    val kitThrdColor2: String,
    val kitThrdColor3: String,
    val kitThrdColor4: String,
    val seasonalKitId: String,
    val seasonalTeamId: String,
    val selectedKitType: String,
    val stadName: String
)