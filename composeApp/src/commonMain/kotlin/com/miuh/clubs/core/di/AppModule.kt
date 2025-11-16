package com.miuh.clubs.core.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import coil3.ImageLoader
import com.miuh.clubs.core.data.GenType
import com.miuh.clubs.core.data.KtorClubsRemoteRepository
import com.miuh.clubs.core.data.LeaderboardType
import com.miuh.clubs.core.data.db.local.ClubDao
import com.miuh.clubs.core.data.db.local.ClubsDatabase
import com.miuh.clubs.core.data.domain.uc.remote.EaDBUseCases
import com.miuh.clubs.core.data.schema.ClubSchemaSearchByName
import com.miuh.clubs.core.data.schema.ClubSchemaTop100
import com.miuh.clubs.domain.uc.remote_db_uc.ClubsRemoteRepository
import com.miuh.clubs.core.data.domain.uc.remote.GetClubCrestAssetByIdUseCase
import com.miuh.clubs.core.data.domain.uc.remote.GetTop100ClubsUseCase
import com.miuh.clubs.domain.uc.remote_db_uc.NetworkingUseCase
import com.miuh.clubs.domain.uc.remote_db_uc.RemoteUseCases
import com.miuh.clubs.core.data.domain.uc.remote.SearchClubByNameUseCase
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.serialization.json.Json
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


    factory<ClubsRemoteRepository> {
        KtorClubsRemoteRepository(get())

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
            localUseCases = get(),
            remoteUseCases = get()
        )
    }

    single<RemoteUseCases> {
        EaDBUseCases(
            searchClubUc = get(SearchClubByName),
            getTop100ClubsUseCase = get(Top100ClubsUc),
            getClubCrestAssetByIdUseCase = get(GetClubCrestImage),
        )
    }

    single<ClubsDatabase> {
        val builder: RoomDatabase.Builder<ClubsDatabase> = get()
        builder
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()

    }

    single<ClubDao> {
        val dataBase: ClubsDatabase = get()
        dataBase.clubsDao()
    }
}
