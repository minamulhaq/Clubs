package com.miuh.clubs.domain

interface ClubsRepository {
    suspend fun searchClubs(): List<String>
}