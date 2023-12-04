package com.patigayon.semifinals.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.patigayon.semifinals.api.ServiceBuilder
import com.patigayon.semifinals.constants.Constants
import com.patigayon.semifinals.databinding.ActivityTweetDetailBinding
import com.patigayon.semifinals.model.Tweet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TweetDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTweetDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTweetDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getTweet()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getTweet() {
        val id = intent.getStringExtra(Constants.PARAM_ID) ?: return
        ServiceBuilder.tweetApiService.getTweet(id).enqueue(object: Callback<Tweet> {
            override fun onResponse(call: Call<Tweet>, response: Response<Tweet>) {
                if (response.isSuccessful) {
                    val tweet: Tweet? = response.body()
                    tweet?.let {
                        binding.textViewTweetName.text = it.name
                        binding.textViewTweetDescription.text = it.description
                        binding.progressBar.visibility = View.GONE
                    }
                } else {
                    showError()
                }
            }

            override fun onFailure(call: Call<Tweet>, t: Throwable) {
                showError()
            }
        })
    }

    private fun showError() {
        Toast.makeText(this, "Failed to load tweet.", Toast.LENGTH_SHORT).show()
    }
}