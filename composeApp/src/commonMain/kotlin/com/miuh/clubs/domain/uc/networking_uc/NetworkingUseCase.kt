package com.miuh.clubs.domain.uc.networking_uc

interface NetworkingUseCase<out T> {
    suspend operator fun invoke(): T
}