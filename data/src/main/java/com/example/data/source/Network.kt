package com.example.data.source


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    fun initRetrofit(base_url: String): Retrofit = Retrofit.Builder()
        .baseUrl(base_url)
        .client(initOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun initOkHttpClient() = OkHttpClient.Builder()
        .build()
}