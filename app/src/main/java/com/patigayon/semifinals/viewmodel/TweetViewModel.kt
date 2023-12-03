package com.patigayon.semifinals.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.patigayon.semifinals.model.Tweet
import com.patigayon.semifinals.repository.TweetRepository
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TweetViewModel(private val repository: TweetRepository) : ViewModel() {

    private val _tweets = MutableLiveData<List<Tweet>>()
    val tweets: LiveData<List<Tweet>> = _tweets

    private val _responseMessage = MutableLiveData<String>()
    val responseMessage: LiveData<String> = _responseMessage

    fun loadTweets() {
        repository.getAllTweets().enqueue(object : Callback<List<Tweet>> {
            override fun onResponse(call: Call<List<Tweet>>, response: Response<List<Tweet>>) {
                if (response.isSuccessful) {
                    _tweets.value = response.body()
                } else {
                    _responseMessage.value = "Error: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                _responseMessage.value = t.message
            }
        })
    }

    fun createTweet(newTweet: Tweet) {
        repository.createTweet(newTweet).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                _responseMessage.value = if (response.isSuccessful) {
                    "Tweet created successfully"
                } else {
                    "Error: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                _responseMessage.value = t.message
            }
        })
    }

    fun updateTweet(tweet: Tweet) {
        repository.updateTweet(tweet.id, tweet).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                _responseMessage.value = if (response.isSuccessful) {
                    "Tweet updated successfully"
                } else {
                    "Error: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                _responseMessage.value = t.message
            }
        })
    }

    fun deleteTweet(tweetId: String) {
        repository.deleteTweet(tweetId).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                _responseMessage.value = if (response.isSuccessful) {
                    "Tweet deleted successfully"
                } else {
                    "Error: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                _responseMessage.value = t.message
            }
        })
    }

    private val _tweet = MutableLiveData<Tweet>()
    val tweet: LiveData<Tweet> = _tweet

    fun getTweet(tweetId: String) {
        repository.getTweet(tweetId).enqueue(object : Callback<Tweet> {
            override fun onResponse(call: Call<Tweet>, response: Response<Tweet>) {
                if (response.isSuccessful) {
                    _tweet.postValue(response.body())
                } else {

                    _tweet.postValue(null)
                }
            }

            override fun onFailure(call: Call<Tweet>, t: Throwable) {
                _tweet.postValue(null)
            }
        })
    }

    fun getTweetDetail(tweetId: String): LiveData<Tweet> {
        getTweet(tweetId)
        return tweet
    }
}
