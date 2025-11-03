package com.miuh.clubs.core.data

import io.ktor.http.URLBuilder
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom

enum class MatchType(val param: String) {
    LEAGUE("leagueMatch"), PLAYOFF("playoffMatch")
}

enum class GameType {
    GEN5, GEN4, NX;

    fun platformParam(): String = when (this) {
        GEN5 -> "common-gen5"
        GEN4 -> "common-gen4"
        NX -> "nx"
    }
}

enum class LeaderboardType(val urlSegment: String, val displayString: String) {
    ALL_TIME("allTimeLeaderboard", "All Time"), CURRENT_SEASON(
        "currentSeasonLeaderboard",
        "Current Season"
    )
}

object ClubsApi {
    const val BASE_URL = "https://proclubs.ea.com"
    const val BASE_PATH = "/api/fc/"            // keep prefix not as segment
    const val PLATFORM_QUERY = "platform"
    const val CLUB_NAME_QUERY = "clubName"

    /**
     * Build leaderboard endpoint URL with correct prefix composition
     */
    /**
     * unified builder for leaderboard + search
     */
    fun buildUrlTop100(
        gameType: GameType, leaderboardType: LeaderboardType
    ): String {
        return URLBuilder().apply {
            // base domain
            takeFrom(BASE_URL)
            encodedPath = BASE_PATH + leaderboardType.urlSegment
            parameters.append(PLATFORM_QUERY, gameType.platformParam())

        }.buildString()
    }

    fun buildUrlClubSearch(
        gameType: GameType, leaderboardType: LeaderboardType, searchClubName: String? = null
    ): String {
        return URLBuilder().apply {
            // base domain
            takeFrom(BASE_URL)

            // safe prefix composition, avoid double slash bugs
            encodedPath =
                BASE_PATH + leaderboardType.urlSegment + if (searchClubName != null) "/search" else ""

            // always add platform
            parameters.append(PLATFORM_QUERY, gameType.platformParam())

            // optional search club name
            searchClubName?.let { name ->
                parameters.append(CLUB_NAME_QUERY, name)
            }
        }.buildString()
    }


    fun buildClubInfoUrl(
        gameType: GameType, clubIds: List<Long>
    ): String {
        return URLBuilder().apply {
            takeFrom(BASE_URL)
            encodedPath = BASE_PATH + "clubs/info"
            parameters.append("platform", gameType.platformParam())
            parameters.append("clubIds", clubIds.joinToString(","))
        }.buildString()
    }


    fun buildClubOverallStatsUrl(
        gameType: GameType, clubIds: List<Long>
    ): String {
        return URLBuilder().apply {
            takeFrom(BASE_URL)
            encodedPath = BASE_PATH + "clubs/overallStats"
            parameters.append("platform", gameType.platformParam())
            parameters.append("clubIds", clubIds.joinToString(","))
        }.buildString()
    }


    fun buildClubPlayoffAchievementsUrl(
        gameType: GameType, clubId: Long
    ): String {
        return URLBuilder().apply {
            takeFrom(BASE_URL)
            encodedPath = BASE_PATH + "club/playoffAchievements"
            parameters.append("platform", gameType.platformParam())
            parameters.append("clubId", clubId.toString())
        }.buildString()
    }


    fun buildClubMembersCareerStatsUrl(
        gameType: GameType, clubId: Long
    ): String {
        return URLBuilder().apply {
            takeFrom(BASE_URL)
            encodedPath = BASE_PATH + "members/career/stats"
            parameters.append("platform", gameType.platformParam())
            parameters.append("clubId", clubId.toString())
        }.buildString()
    }

    fun buildClubMembersStatsUrl(
        gameType: GameType, clubId: Long
    ): String {
        return URLBuilder().apply {
            takeFrom(BASE_URL)
            encodedPath = BASE_PATH + "members/stats"
            parameters.append("platform", gameType.platformParam())
            parameters.append("clubId", clubId.toString())
        }.buildString()
    }

    fun buildClubMatchesUrl(
        gameType: GameType, clubIds: List<Long>, matchType: String, maxResultCount: Int = 1
    ): String {
        return URLBuilder().apply {
            takeFrom(BASE_URL)
            encodedPath = BASE_PATH + "clubs/matches"
            parameters.append("platform", gameType.platformParam())
            parameters.append("clubIds", clubIds.joinToString(","))
            parameters.append("matchType", matchType)
            parameters.append("maxResultCount", maxResultCount.toString())
        }.buildString()
    }


    fun buildClubMatchesUrl(
        gameType: GameType, clubIds: List<Long>, matchType: MatchType, maxResultCount: Int = 1
    ): String {
        return URLBuilder().apply {
            takeFrom(BASE_URL)
            encodedPath = BASE_PATH + "clubs/matches"
            parameters.append("platform", gameType.platformParam())
            parameters.append("clubIds", clubIds.joinToString(","))
            parameters.append("matchType", matchType.param)
            parameters.append("maxResultCount", maxResultCount.toString())
        }.buildString()
    }


}


