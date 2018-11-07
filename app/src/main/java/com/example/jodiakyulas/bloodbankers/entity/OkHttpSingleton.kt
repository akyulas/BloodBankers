package com.example.jodiakyulas.bloodbankers.entity

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object OkHttpSingleton {

    private val client = OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).build()

    fun getClient() : OkHttpClient {
        return client
    }


}