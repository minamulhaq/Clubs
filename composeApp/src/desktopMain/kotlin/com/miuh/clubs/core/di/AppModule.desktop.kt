package com.miuh.clubs.core.di

import coil3.ImageLoader
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.module

actual val platformDataModule: Module = module {

    single<HttpClientEngine> {
        OkHttp.create()
    }
}

actual fun Scope.createImageLoader(): ImageLoader {
    TODO("Not yet implemented")
}