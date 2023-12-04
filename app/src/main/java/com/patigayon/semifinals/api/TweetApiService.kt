package com.patigayon.semifinals.api

import com.patigayon.semifinals.model.Tweet
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TweetApiService {
    @GET("tweet/patigayon")
    fun getAllTweets(): Call<List<Tweet>>

    @GET("tweet/patigayon/{tweet_id}")
    fun getTweet(@Path("tweet_id") tweetId: String): Call<Tweet>

    @POST("tweet/patigayon")
    fun createTweet(@Body newTweet: Tweet): Call<Tweet>

    @PUT("tweet/patigayon/{tweet_id}")
    fun updateTweet(@Path("tweet_id") tweetId: String, @Body tweet: Tweet): Call<Tweet>

    @DELETE("tweet/patigayon/{tweet_id}")
    fun deleteTweet(@Path("tweet_id") tweetId: String): Call<Tweet>
}
