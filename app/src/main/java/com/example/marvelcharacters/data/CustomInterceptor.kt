package com.example.marvelcharacters.data

import com.example.domain.Constant.ConnectionUtils.PUB_KEY
import com.example.domain.Constant.ConnectionUtils.PV_KEY
import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest
import java.util.*

class CustomInterceptor : Interceptor {
    private val ts = Date().time
    private val hash = getHash("${ts}${PV_KEY}${PUB_KEY}")


    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url().newBuilder()
            .addQueryParameter("apikey", PUB_KEY)
            .addQueryParameter("hash",hash)
            .addQueryParameter("ts", ts.toString())
            .build()
        val request = chain.request().newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }

    private fun getHash(str : String) : String{
        val bytes = MessageDigest.getInstance("MD5").digest(str.toByteArray())
        return bytes.joinToString(""){
            "%02x".format(it)
        }
    }
}