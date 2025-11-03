package com.miuh.clubs.core.di

import com.miuh.clubs.core.data.GenType
import com.miuh.clubs.core.data.HttpClientEngineFactory
import com.miuh.clubs.core.data.KtorClubsRepository
import com.miuh.clubs.core.data.LeaderboardType
import com.miuh.clubs.core.data.schema.ClubSchemaSearchByName
import com.miuh.clubs.core.data.schema.ClubSchemaTop100
import com.miuh.clubs.domain.ClubsRepository
import com.miuh.clubs.domain.uc.networking_uc.GetTop100ClubsUseCase
import com.miuh.clubs.domain.uc.networking_uc.NetworkingUseCase
import com.miuh.clubs.domain.uc.networking_uc.SearchClubByNameUseCase
import com.miuh.clubs.presentation.ClubsViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Module
class AppModule {

    @Single
    fun httpClient(engine: HttpClientEngine): HttpClient {
        return HttpClient(engine) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                    })
            }
            install(Auth) {}
            defaultRequest {
                contentType(ContentType.Application.Json)
            }
        }
    }

    @Factory
    fun httpClientEngine(): HttpClientEngine = HttpClientEngineFactory().getHttpEngine()

    @Factory(binds = [ClubsRepository::class])
    fun clubsRepository(httpClient: HttpClient) = KtorClubsRepository(httpClient)

    @Single(binds = [NetworkingUseCase::class])
    @Top100ClubsUc
    fun top100Uc(repository: ClubsRepository) = GetTop100ClubsUseCase(repository)

    @Single(binds = [NetworkingUseCase::class])
    @SearchClubByName
    fun searchClubByName(repository: ClubsRepository) = SearchClubByNameUseCase(repository)

    @KoinViewModel
    fun clubsViewModel(
        @Top100ClubsUc top100ClubsUseCase: NetworkingUseCase<GenType, LeaderboardType, Unit?, List<ClubSchemaTop100>>,
        @SearchClubByName searchClubByNameUseCase: NetworkingUseCase<GenType, LeaderboardType, String, List<ClubSchemaSearchByName>>,
    ) =
        ClubsViewModel(top100ClubsUseCase, searchClubByNameUseCase)
}


@Named
annotation class Top100ClubsUc

@Named
annotation class SearchClubByName
