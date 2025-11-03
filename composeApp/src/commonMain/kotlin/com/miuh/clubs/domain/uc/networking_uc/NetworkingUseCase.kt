package com.miuh.clubs.domain.uc.networking_uc


interface NetworkingUseCase<in P, in Q, in R, out T> {
    suspend operator fun invoke(p: P? = null, q: Q? = null, r: R? = null): T
}