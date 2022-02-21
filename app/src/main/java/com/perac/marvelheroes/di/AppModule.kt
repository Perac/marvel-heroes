package com.perac.marvelheroes.di

import com.google.gson.Gson
import com.perac.marvelheroes.network.repository.MarvelHeroesRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

/**
 * Koin module with app level dependencies.
 */
val appModule = module {

    single<Gson> { Gson() }

    single<OkHttpClient> {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            })
            .build()
    }

    single<MarvelHeroesRepository> { MarvelHeroesRepository(gson = get(), okHttpClient = get()) }
}