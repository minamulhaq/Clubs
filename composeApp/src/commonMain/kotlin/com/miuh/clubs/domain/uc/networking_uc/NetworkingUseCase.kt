package com.miuh.clubs.domain.uc.networking_uc

interface NetworkingUseCase<in P, out T> {
    suspend operator fun invoke(param: P): T
}