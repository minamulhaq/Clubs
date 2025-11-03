package com.miuh.clubs.domain.uc.networking_uc

import com.miuh.clubs.core.data.LeaderboardType

interface NetworkingUseCase<in P, in Q, out T> {
    suspend operator fun invoke(genType: P, leaderboardType: Q): T
}