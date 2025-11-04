package com.miuh.clubs.core.di

import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {

    startKoin {
        printLogger(Level.DEBUG)
        config?.invoke(this)
        modules(
            platformDataModule,
            appModule,
        )
    }
}