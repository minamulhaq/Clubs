package com.miuh.clubs.domain

import com.miuh.clubs.core.data.schema.ClubSchemaTop100
import com.miuh.clubs.core.util.Error
import com.miuh.clubs.core.util.Result


interface ClubsRepository {
    suspend fun searchClubs(): List<ClubSchemaTop100>

}