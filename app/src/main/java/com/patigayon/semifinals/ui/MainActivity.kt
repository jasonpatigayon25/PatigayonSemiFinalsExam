package com.patigayon.semifinals.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.patigayon.semifinals.adapters.TweetAdapter
import com.patigayon.semifinals.api.ServiceBuilder
import com.patigayon.semifinals.databinding.ActivityMainBinding
import com.patigayon.semifinals.model.Tweet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupFab()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this).apply {
            reverseLayout = true
            stackFromEnd = true
        }
        binding.recyclerViewTweets.layoutManager = layoutManager
    }

    private fun setupFab() {
        binding.fabCreateTweet.setOnClickListener {
            startActivity(Intent(this, CreateTweetActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadTweets()
    }

    private fun loadTweets() {
        ServiceBuilder.tweetApiService.getAllTweets().enqueue(object: Callback<List<Tweet>> {
            override fun onResponse(call: Call<List<Tweet>>, response: Response<List<Tweet>>) {
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        binding.recyclerViewTweets.adapter = TweetAdapter(this@MainActivity, data)
                        binding.progressBar.visibility = View.GONE
                    }
                } else {
                    showError()
                }
            }

            override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                showError()
            }
        })
    }

    private fun showError() {
        Toast.makeText(this, "Failed to load data.", Toast.LENGTH_SHORT).show()
    }
}