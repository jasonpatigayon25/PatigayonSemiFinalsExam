package com.patigayon.semifinals.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.patigayon.semifinals.R
import com.patigayon.semifinals.api.ServiceBuilder
import com.patigayon.semifinals.constants.Constants
import com.patigayon.semifinals.databinding.ActivityEditTweetBinding
import com.patigayon.semifinals.model.Tweet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditTweetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditTweetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTweetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tweetId = intent.getStringExtra(Constants.PARAM_ID)
        if (tweetId != null) {
            loadTweet(tweetId)
        }

        binding.buttonUpdateTweet.setOnClickListener {
            if (tweetId != null) {
                updateTweet(tweetId)
            }
        }
    }

    private fun loadTweet(tweetId: String) {
        ServiceBuilder.tweetApiService.getTweet(tweetId).enqueue(object : Callback<Tweet> {
            override fun onResponse(call: Call<Tweet>, response: Response<Tweet>) {
                if (response.isSuccessful) {
                    response.body()?.let { tweet ->
                        binding.editTweetName.setText(tweet.name)
                        binding.editTweetDescription.setText(tweet.description)
                    }
                } else {
                    showError("Error loading tweet")
                }
            }

            override fun onFailure(call: Call<Tweet>, t: Throwable) {
                showError("Failed to load tweet")
            }
        })
    }

    private fun updateTweet(tweetId: String) {
        val updatedTweet = Tweet(
            id = tweetId,
            name = binding.editTweetName.text.toString(),
            description = binding.editTweetDescription.text.toString()
        )

        ServiceBuilder.tweetApiService.updateTweet(tweetId, updatedTweet).enqueue(object : Callback<Tweet> {
            override fun onResponse(call: Call<Tweet>, response: Response<Tweet>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@EditTweetActivity, "Tweet updated successfully", Toast.LENGTH_SHORT).show()
                    finish() // Close the activity after successful update
                } else {
                    showError("Error updating tweet")
                }
            }

            override fun onFailure(call: Call<Tweet>, t: Throwable) {
                showError("Failed to update tweet")
            }
        })
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
