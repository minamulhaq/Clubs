package com.miuh.clubs.core.data

import com.miuh.clubs.core.data.schema.ClubSchemaTop100
import com.miuh.clubs.core.util.Error
import com.miuh.clubs.core.util.Result
import com.miuh.clubs.domain.ClubsRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class KtorClubsRepository(
    val httpClient: HttpClient
) : ClubsRepository {
    override suspend fun searchClubs(): List<ClubSchemaTop100> {
        when (val r: Result<String, Error> = getTop100()) {
            is Result.Error<*> -> {
                println("ERROR searchClubs\n\n\n${r.error}\n\n\n")
                return emptyList()
            }

            is Result.Success<*> -> {
                println("Clubs searchClubs\n\n\n${r.data}\n\n\n")

                val jsonString = r.data as String
                return try {
                    Json.decodeFromString(ListSerializer(ClubSchemaTop100.serializer()), jsonString)
                } catch (e: Exception) {
                    e.printStackTrace()
                    return emptyList()
                }
            }
        }
    }

    suspend inline fun <reified R : Any> getTop100(
        route: String = "", parameters: Map<String, Any> = emptyMap()
    ): Result<R, Error> {
        return NetworkResponseParser().safeCall<R> {
            var url = ClubsApi.buildUrlTop100(
                gameType = GameType.GEN5,
                leaderboardType = LeaderboardType.ALL_TIME,
//                searchClubName = "gulagis"
            )
            println("URL is: $url")

//            url = ClubsApi.buildClubInfoUrl(
//                gameType = GameType.GEN5, clubIds = listOf(490431)
//            )
//
//            url = ClubsApi.buildClubOverallStatsUrl(
//                gameType = GameType.GEN5, clubIds = listOf(490431)
//            )
//
//            url = ClubsApi.buildClubMembersStatsUrl(
//                gameType = GameType.GEN5, clubId = 490431
//            )
            httpClient.get(url)
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
