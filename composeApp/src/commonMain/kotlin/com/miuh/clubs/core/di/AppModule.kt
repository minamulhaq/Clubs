package com.miuh.clubs.core.di

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.util.DebugLogger
import com.miuh.clubs.core.data.GenType
import com.miuh.clubs.core.data.KtorClubsRepository
import com.miuh.clubs.core.data.LeaderboardType
import com.miuh.clubs.core.data.schema.ClubSchemaSearchByName
import com.miuh.clubs.core.data.schema.ClubSchemaTop100
import com.miuh.clubs.domain.ClubsRepository
import com.miuh.clubs.domain.uc.networking_uc.GetClubCrestAssetByIdUseCase
import com.miuh.clubs.domain.uc.networking_uc.GetTop100ClubsUseCase
import com.miuh.clubs.domain.uc.networking_uc.NetworkingUseCase
import com.miuh.clubs.domain.uc.networking_uc.SearchClubByNameUseCase
import com.miuh.clubs.presentation.ClubsViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okio.FileSystem
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module


expect val platformDataModule: Module
expect fun Scope.createImageLoader(): ImageLoader


val Top100ClubsUc = named("Top100ClubsUc")
val SearchClubByName = named("SearchClubByName")
val GetClubCrestImage = named("GetClubCrestImage")


val appModule = module {
    single {
        HttpClient(get()) {
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


    factory<ClubsRepository> {
        KtorClubsRepository(get())

    }



    single<NetworkingUseCase<GenType, LeaderboardType, String?, List<ClubSchemaTop100>>>(
        Top100ClubsUc
    ) {
        GetTop100ClubsUseCase(get())
    }

    single<NetworkingUseCase<GenType, LeaderboardType, String, List<ClubSchemaSearchByName>>>(
        SearchClubByName
    ) {
        SearchClubByNameUseCase(get())
    }

    single<NetworkingUseCase<String, Unit?, Unit?, String>>(GetClubCrestImage) {
        GetClubCrestAssetByIdUseCase(get())
    }

    single<ImageLoader> {
        createImageLoader()
    }

    viewModel {
        ClubsViewModel(
            get(Top100ClubsUc),
            get(SearchClubByName),
            get(GetClubCrestImage),
            get()
        )
    }




}
