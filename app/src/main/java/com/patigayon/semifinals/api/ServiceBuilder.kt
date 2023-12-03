package com.patigayon.semifinals.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://eldroid.online/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val tweetApiService: TweetApiService = retrofit.create(TweetApiService::class.java)
}