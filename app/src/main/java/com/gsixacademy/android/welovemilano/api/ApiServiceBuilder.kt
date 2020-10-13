package com.gsixacademy.android.welovemilano.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceBuilder {
    val client= OkHttpClient.Builder().build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://developers.zomato.com/api/v2.1/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun<T>buildService(service:Class<T>): T{
        return retrofit.create(service)
    }
}