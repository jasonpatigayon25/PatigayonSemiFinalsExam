package com.patigayon.semifinals.api

import com.patigayon.semifinals.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private const val BASE_URL = Constants.API

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val tweetApiService: TweetApiService = retrofit.create(TweetApiService::class.java)
}