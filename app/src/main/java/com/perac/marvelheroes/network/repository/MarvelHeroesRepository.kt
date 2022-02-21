package com.perac.marvelheroes.network.repository

import com.google.gson.Gson
import com.perac.marvelheroes.network.api.MarvelApiService
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import okhttp3.OkHttpClient
import org.apache.commons.codec.digest.DigestUtils
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest

class MarvelHeroesRepository(
    gson: Gson,
    okHttpClient: OkHttpClient
) {

    private companion object {
        private const val BASE_URL = "https://gateway.marvel.com/"
        private const val API_KEY = "bccd52a1cfee2bf738851755adf9ee7c"
        private const val TIMESTAMP = "perac"
        private const val PRIVATE_KEY = "a40c3cd57593d0bd781f269ee41c842c66d63f1e"
        private const val HASH = "$TIMESTAMP$PRIVATE_KEY$API_KEY"
    }

    private val apiSubject = BehaviorSubject.create<MarvelApiService>()
    private var hash: String? = null

    init {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MarvelApiService::class.java)
            .also {
                apiSubject.onNext(it)
            }
    }

    private fun getApi() = apiSubject
        .subscribeOn(Schedulers.io())


    fun fetchHeroesList(searchParam: String? = null) = getApi()
        .flatMapSingle {
            it.fetchHeroList(
                searchParam = searchParam,
                publicKey = API_KEY,
                timestamp = TIMESTAMP,
                hash = hash ?: buildHash()
            )
        }

    fun fetchHeroDetails(heroId: String) = getApi()
        .flatMapSingle {
            it.fetchHeroDetails(
                heroId,
                publicKey = API_KEY,
                timestamp = TIMESTAMP,
                hash = hash ?: buildHash()
            )
        }

    private fun buildHash(): String {
        val md = MessageDigest.getInstance("MD5")
        val digestUtils = DigestUtils(md)
        hash = digestUtils.digestAsHex(HASH)
        return hash!!
    }
}