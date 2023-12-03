package com.patigayon.semifinals.repository

import com.patigayon.semifinals.viewmodel.TweetViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.patigayon.semifinals.api.ServiceBuilder

class TweetViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TweetViewModel::class.java)) {
            val apiService = ServiceBuilder.tweetApiService
            val repository = TweetRepository(apiService)
            @Suppress("UNCHECKED_CAST")
            return TweetViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
