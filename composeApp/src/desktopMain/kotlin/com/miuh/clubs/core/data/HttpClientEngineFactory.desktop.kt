package com.miuh.clubs.core.data

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.annotation.Single

actual class HttpClientEngineFactory actual constructor() {
    actual fun getHttpEngine(): HttpClientEngine {
        return OkHttp.create()
    }
}