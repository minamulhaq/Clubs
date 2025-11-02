package com.miuh.clubs

import android.app.Application
import com.miuh.clubs.core.di.initKoin
import org.koin.android.ext.koin.androidContext

class ClubsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@ClubsApplication)
        }
    }
}