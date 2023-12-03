package com.patigayon.semifinals.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.patigayon.semifinals.R
import com.patigayon.semifinals.viewmodel.TweetViewModel

class TweetDetailActivity : AppCompatActivity() {
    // Assume you pass the tweet ID through intent
    private lateinit var viewModel  : TweetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet_detail)

        val tweetId = intent.getStringExtra("TWEET_ID")
        setupViewModel(tweetId)
    }

    private fun setupViewModel(tweetId: String?) {
        viewModel = ViewModelProvider(this)[TweetViewModel::class.java]
        tweetId?.let {
            viewModel.getTweetDetail(it).observe(this) { tweet ->
                // Update UI with tweet details
            }
        }
    }
}