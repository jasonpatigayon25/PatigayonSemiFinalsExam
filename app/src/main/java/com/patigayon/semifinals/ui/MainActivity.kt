package com.patigayon.semifinals.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.patigayon.semifinals.R
import com.patigayon.semifinals.repository.TweetViewModelFactory
import com.patigayon.semifinals.viewmodel.TweetViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: TweetViewModel
    private lateinit var adapter: TweetListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupRecyclerView()
        setupAddButton()

        viewModel.tweets.observe(this, { tweets ->
            adapter.submitList(tweets)
        })

        viewModel.loadTweets()
    }

    private fun setupRecyclerView() {
        adapter = TweetListAdapter { tweet ->
            val intent = Intent(this, TweetDetailActivity::class.java)
            intent.putExtra("tweet_id", tweet.id)
            startActivity(intent)
        }
        findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter
    }

    private fun setupViewModel() {
        val factory = TweetViewModelFactory()
        viewModel = ViewModelProvider(this, factory)[TweetViewModel::class.java]
    }

    private fun setupAddButton() {
        val fabCreateTweet = findViewById<FloatingActionButton>(R.id.fabCreateTweet)
        fabCreateTweet.setOnClickListener {
            val intent = Intent(this, CreateTweetActivity::class.java)
            startActivity(intent)
        }
    }
}

