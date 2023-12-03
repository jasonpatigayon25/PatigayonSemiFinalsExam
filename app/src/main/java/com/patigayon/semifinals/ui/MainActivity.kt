package com.patigayon.semifinals.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        setupRecyclerView()
        setupViewModel()
        setupAddButton()
    }

    private fun setupRecyclerView() {
        adapter = TweetListAdapter { tweet ->
            // Handle click event, maybe open TweetDetailActivity
        }
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupViewModel() {
        val factory = TweetViewModelFactory()
        viewModel = ViewModelProvider(this, factory)[TweetViewModel::class.java]

        viewModel.tweets.observe(this) { tweets ->
            adapter.submitList(tweets)
        }
        viewModel.loadTweets()
    }

    private fun setupAddButton() {
        val fabCreateTweet = findViewById<FloatingActionButton>(R.id.fabCreateTweet)
        fabCreateTweet.setOnClickListener {
            // Intent to start CreateTweetActivity
            val intent = Intent(this, CreateTweetActivity::class.java)
            startActivity(intent)
        }
    }

}
