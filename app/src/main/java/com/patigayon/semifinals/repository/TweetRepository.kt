package com.patigayon.semifinals.repository

import com.patigayon.semifinals.api.TweetApiService
import com.patigayon.semifinals.model.Tweet
import retrofit2.Call
import okhttp3.ResponseBody

class TweetRepository(private val apiService: TweetApiService) {

    // Fetch all tweets
    fun getAllTweets(): Call<List<Tweet>> {
        return apiService.getAllTweets()
    }

    // Fetch a specific tweet
    fun getTweet(tweetId: String): Call<Tweet> {
        return apiService.getTweet(tweetId)
    }

    // Create a new tweet
    fun createTweet(newTweet: Tweet): Call<ResponseBody> {
        return apiService.createTweet(newTweet)
    }

    // Update an existing tweet
    fun updateTweet(tweetId: String, tweet: Tweet): Call<ResponseBody> {
        return apiService.updateTweet(tweetId, tweet)
    }

    // Delete a tweet
    fun deleteTweet(tweetId: String): Call<ResponseBody> {
        return apiService.deleteTweet(tweetId)
    }
}
