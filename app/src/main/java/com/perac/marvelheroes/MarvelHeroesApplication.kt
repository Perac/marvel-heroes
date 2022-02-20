package com.perac.marvelheroes

import android.app.Application
import com.perac.marvelheroes.di.appModule
import com.perac.marvelheroes.di.fragmentModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MarvelHeroesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MarvelHeroesApplication)
            modules(appModule, fragmentModule)
        }
    }
}