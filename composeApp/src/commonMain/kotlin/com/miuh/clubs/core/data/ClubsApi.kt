package com.miuh.clubs.core.data

import io.ktor.http.URLBuilder
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom

enum class MatchType(val param: String) {
    LEAGUE("leagueMatch"), PLAYOFF("playoffMatch"), FRIENDLY("friendlyMatch")
}

enum class GenType {
    GEN5, GEN4, NX;

    fun platformParam(): String = when (this) {
        GEN5 -> "common-gen5"
        GEN4 -> "common-gen4"
        NX -> "nx"
    }
}

enum class LeaderboardType(val urlSegment: String, val displayString: String) {
    ALL_TIME("allTimeLeaderboard", "All Time"), CURRENT_SEASON(
        "currentSeasonLeaderboard", "Current Season"
    )
}

object ClubsApi {
    const val BASE_URL = "https://proclubs.ea.com"
    const val BASE_PATH = "/api/fc/"            // keep prefix not as segment
    const val PLATFORM_QUERY = "platform"
    const val CLUB_NAME_QUERY = "clubName"


    const val CREST_BASE_URL =
        "https://eafc24.content.easports.com/fifa/fltOnlineAssets/24B23FDE-7835-41C2-87A2-F453DFDB2E82/2024/fcweb/crests/256x256/l"
    const val CREST_SUFFIX = ".png"


    fun buildUrl(
        genType: GenType, leaderboardType: LeaderboardType, searchClubName: String? = null
    ): String {
        return URLBuilder().apply {
            // base domain
            takeFrom(BASE_URL)

            // safe prefix composition, avoid double slash bugs
            encodedPath =
                BASE_PATH + leaderboardType.urlSegment + if (searchClubName != null) "/search" else ""

            // always add platform
            parameters.append(PLATFORM_QUERY, genType.platformParam())

            // optional search club name
            searchClubName?.let { name ->
                parameters.append(CLUB_NAME_QUERY, name)
            }
        }.buildString()
    }


    fun buildClubInfoUrl(
        genType: GenType, clubIds: List<Long>
    ): String {
        return URLBuilder().apply {
            takeFrom(BASE_URL)
            encodedPath = BASE_PATH + "clubs/info"
            parameters.append("platform", genType.platformParam())
            parameters.append("clubIds", clubIds.joinToString(","))
        }.buildString()
    }


    fun buildClubOverallStatsUrl(
        genType: GenType, clubIds: List<Long>
    ): String {
        return URLBuilder().apply {
            takeFrom(BASE_URL)
            encodedPath = BASE_PATH + "clubs/overallStats"
            parameters.append("platform", genType.platformParam())
            parameters.append("clubIds", clubIds.joinToString(","))
        }.buildString()
    }


    fun buildClubPlayoffAchievementsUrl(
        genType: GenType, clubId: Long
    ): String {
        return URLBuilder().apply {
            takeFrom(BASE_URL)
            encodedPath = BASE_PATH + "club/playoffAchievements"
            parameters.append("platform", genType.platformParam())
            parameters.append("clubId", clubId.toString())
        }.buildString()
    }


    fun buildClubMembersCareerStatsUrl(
        genType: GenType, clubId: Long
    ): String {
        return URLBuilder().apply {
            takeFrom(BASE_URL)
            encodedPath = BASE_PATH + "members/career/stats"
            parameters.append("platform", genType.platformParam())
            parameters.append("clubId", clubId.toString())
        }.buildString()
    }

    fun buildClubMembersStatsUrl(
        genType: GenType, clubId: Long
    ): String {
        return URLBuilder().apply {
            takeFrom(BASE_URL)
            encodedPath = BASE_PATH + "members/stats"
            parameters.append("platform", genType.platformParam())
            parameters.append("clubId", clubId.toString())
        }.buildString()
    }

    fun buildClubMatchesUrl(
        genType: GenType, clubIds: List<Long>, matchType: MatchType, maxResultCount: Int = 1
    ): String {
        return URLBuilder().apply {
            takeFrom(BASE_URL)
            encodedPath = BASE_PATH + "clubs/matches"
            parameters.append("platform", genType.platformParam())
            parameters.append("clubIds", clubIds.joinToString(","))
            parameters.append("matchType", matchType.param)
            parameters.append("maxResultCount", maxResultCount.toString())
        }.buildString()
    }


    fun buildClubCrestAssetUrl(crestID: String): String {
        val fullUrl = "$CREST_BASE_URL$crestID$CREST_SUFFIX"
        return fullUrl
    }

}


