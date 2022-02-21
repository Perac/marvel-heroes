package com.perac.marvelheroes.network.api

import com.perac.marvelheroes.network.models.CharacterDataWrapper
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiService {

    @GET("/v1/public/characters")
    fun fetchHeroList(
        @Query("nameStartsWith") searchParam: String?,
        @Query("apikey") publicKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String
    ): Single<CharacterDataWrapper>

    @GET("/v1/public/characters/{characterId}")
    fun fetchHeroDetails(
        @Path("characterId") heroId: String,
        @Query("apikey") publicKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String
    ): Single<CharacterDataWrapper>
}