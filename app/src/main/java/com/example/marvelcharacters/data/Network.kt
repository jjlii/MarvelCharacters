package com.example.marvelcharacters.data

import com.example.domain.Constant.ConnectionUtils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    fun initRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(initOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun initOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(CustomInterceptor())
        .build()
}