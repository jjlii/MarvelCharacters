package com.example.marvelcharacters.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterRetrofit {

    @GET("characters")
    suspend fun getAllCharacters(@Query("ts")ts: Long,
                                @Query("apikey")apikey: String,
                                @Query("hash")hash:String): Response<CharacterResp>

    @GET("characters/{characterId}")
    suspend fun getCharacterById(@Path("characterId")characterId: Long) : Response<CharacterResp>

}