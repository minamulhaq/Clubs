package com.miuh.clubs.core.di

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.util.DebugLogger
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import okio.FileSystem
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.module

actual val platformDataModule: Module = module {
    single<HttpClientEngine> {
        Darwin.create()
    }
    single<PlatformContext> {
        PlatformContext.INSTANCE
    }
}


actual fun Scope.createImageLoader(): ImageLoader {
    val context = PlatformContext.INSTANCE // iOS doesn't need Context injection

    return ImageLoader.Builder(context)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .memoryCache {
            MemoryCache.Builder()
                .maxSizePercent(context, 0.3)
                .strongReferencesEnabled(true)
                .build()
        }
        .diskCachePolicy(CachePolicy.ENABLED)
        .diskCache {
            DiskCache.Builder()
                .directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "clubs_cache")
                .maxSizeBytes(512L * 1024 * 1024)
                .build()
        }
        .networkCachePolicy(CachePolicy.ENABLED)
        .logger(DebugLogger())
        .build()
}