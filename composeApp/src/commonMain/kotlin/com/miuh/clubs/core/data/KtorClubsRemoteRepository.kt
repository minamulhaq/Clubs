package com.miuh.clubs.core.data

import com.miuh.clubs.core.data.schema.ClubSchemaSearchByName
import com.miuh.clubs.core.data.schema.ClubSchemaTop100
import com.miuh.clubs.core.util.Error
import com.miuh.clubs.core.util.Result
import com.miuh.clubs.domain.uc.remote_db_uc.ClubsRemoteRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class KtorClubsRemoteRepository(
    val httpClient: HttpClient
) : ClubsRemoteRepository {


    override suspend fun getTop100(
        genType: GenType, leaderboardType: LeaderboardType, clubName: String?
    ): List<ClubSchemaTop100> {
        when (val r: Result<String, Error> =
            getTop100Clubs(genType = genType, leaderboardType = leaderboardType)) {
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


    // Algorithm Name: Generics Type Resolution
// UseCase: Casting a result data type when using a type-safe wrapper (like Result).

    override suspend fun searchClubByName(
        genType: GenType, leaderboardType: LeaderboardType, name: String?
    ): List<ClubSchemaSearchByName> {

        if (name.isNullOrBlank()) {
            return emptyList()
        }

        // A: The r variable is typed as Result<List<ClubSchemaSearchByName>>
        val r = NetworkResponseParser().safeCall<List<ClubSchemaSearchByName>> {
            val url = ClubsApi.buildUrl(
                genType = genType, leaderboardType = leaderboardType, searchClubName = name
            )
            println("URL is: $url")
            httpClient.get(url).body()
        }

        when (r) {
            is Result.Error<*> -> {
                println("SearchClubByName Error: ${r.error}")
            }

            is Result.Success<List<ClubSchemaSearchByName>> -> {
                return r.data
            }
        }
        return emptyList()
    }

    suspend inline fun <reified R : Any> getTop100Clubs(
        genType: GenType, leaderboardType: LeaderboardType
    ): Result<R, Error> {
        return NetworkResponseParser().safeCall<R> {
            var url = ClubsApi.buildUrl(
                genType = genType, leaderboardType = leaderboardType, searchClubName = null
            )
            println("URL is: $url")

//            url = ClubsApi.buildClubInfoUrl(
//                genType = GenType.GEN5, clubIds = listOf(490431)
//            )
//
//            url = ClubsApi.buildClubOverallStatsUrl(
//                genType = GenType.GEN5, clubIds = listOf(490431)
//            )
//
//            url = ClubsApi.buildClubMembersStatsUrl(
//                genType = GenType.GEN5, clubId = 490431
//            )
            httpClient.get(url)
        }
    }

    override suspend fun getClubCrestById(crestID: String): String {
        val fullUrl = ClubsApi.buildClubCrestAssetUrl(crestID)
        return fullUrl
        /*
        val r = NetworkResponseParser().safeCall<ByteArray> {
            httpClient.get(fullUrl).body()
        }

        return when (r) {
            is Result.Error<*> -> null
            is Result.Success<*> -> r.data as ByteArray
        }
         */
    }
}