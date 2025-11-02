package com.miuh.clubs.core.di

import com.miuh.clubs.core.data.HttpClientEngineFactory
import com.miuh.clubs.core.data.KtorClubsRepository
import com.miuh.clubs.domain.ClubsRepository
import com.miuh.clubs.presentation.ClubsViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class AppModule {

    @Single
    fun httpClient(engine: HttpClientEngine): HttpClient = HttpClient(engine) {}

    @Factory
    fun httpClientEngine(): HttpClientEngine = HttpClientEngineFactory().getHttpEngine()

    @Factory(binds = [ClubsRepository::class])
    fun clubsRepository(httpClient: HttpClient) = KtorClubsRepository(httpClient)


    @KoinViewModel
    fun clubsViewModel(repository: ClubsRepository) = ClubsViewModel(repository)

}