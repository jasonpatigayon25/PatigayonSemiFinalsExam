package com.patigayon.semifinals.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.patigayon.semifinals.api.ServiceBuilder
import com.patigayon.semifinals.databinding.ActivityCreateTweetBinding
import com.patigayon.semifinals.model.Tweet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class   CreateTweetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateTweetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTweetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSubmitTweet.setOnClickListener {
            if(!binding.tweetDescription.text.isNullOrBlank()) {
                createPost()
            }
        }
        binding.backButton.setOnClickListener {
            finish()
        }
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

    private fun createPost() {
        val post = Tweet(
            id = "",
            name = binding.tweetName.text.toString(),
            description = binding.tweetDescription.text.toString()
        )
        ServiceBuilder.tweetApiService.createTweet(post).enqueue(object: Callback<Tweet> {
            override fun onResponse(call: Call<Tweet>, response: Response<Tweet>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@CreateTweetActivity, "Tweet added successfully", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    showError("Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Tweet>, t: Throwable) {
                showError("Failure: ${t.localizedMessage}")
            }
        })
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}