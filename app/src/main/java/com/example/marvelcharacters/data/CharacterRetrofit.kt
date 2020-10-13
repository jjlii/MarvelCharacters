package com.example.marvelcharacters.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterRetrofit {

    @GET("characters")
    suspend fun getAllCharacters(): Response<CharacterResp>

    @GET("characters/{characterId}")
    suspend fun getCharacterById(@Path("characterId")characterId: Int) : Response<CharacterResp>

}