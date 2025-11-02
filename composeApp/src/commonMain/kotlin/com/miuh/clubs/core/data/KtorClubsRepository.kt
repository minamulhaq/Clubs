package com.miuh.clubs.core.data

import com.miuh.clubs.domain.ClubsRepository
import io.ktor.client.HttpClient

class KtorClubsRepository(
    private val httpClient: HttpClient
) : ClubsRepository {
    override suspend fun searchClubs(): List<String> {
        return (1..10).map {
            "Club: $it"
        }
    }

}




/*
Search the top100 clubs -

filter = gentype
filter2 = allTimeLeaderboard / currentSeasonLeaderboard

https://proclubs.ea.com/api/fc/allTimeLeaderboard?platform=common-gen5
https://proclubs.ea.com/api/fc/currentSeasonLeaderboard?platform=common-gen5

https://proclubs.ea.com/api/fc/allTimeLeaderboard?platform=common-gen4
https://proclubs.ea.com/api/fc/allTimeLeaderboard?platform=nx


search club:
https://proclubs.ea.com/api/fc/currentSeasonLeaderboard/search?platform=common-gen5&clubName=gulagis

 */
