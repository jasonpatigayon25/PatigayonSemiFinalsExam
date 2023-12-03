package com.patigayon.semifinals.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.patigayon.semifinals.R
import com.patigayon.semifinals.model.Tweet
import com.patigayon.semifinals.repository.TweetViewModelFactory
import com.patigayon.semifinals.viewmodel.TweetViewModel
import java.util.*

class CreateTweetActivity : AppCompatActivity() {
    private lateinit var viewModel: TweetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_or_edit_tweet)

        setupViewModel()

        val tweetNameEditText = findViewById<EditText>(R.id.editTweetName)
        val tweetDescriptionEditText = findViewById<EditText>(R.id.editTweetDescription)
        val submitButton = findViewById<Button>(R.id.buttonSubmitTweet)

        submitButton.setOnClickListener {
            val name = tweetNameEditText.text.toString()
            val description = tweetDescriptionEditText.text.toString()
            if (name.isNotEmpty() && description.isNotEmpty()) {
                val id = UUID.randomUUID().toString()
                val timestamp = System.currentTimeMillis()
                val newTweet = Tweet(id = id, name = name, description = description, timestamp = timestamp)
                viewModel.createTweet(newTweet)
                // Optionally handle the response or close the activity
                finish() // Close this activity and return to the previous one
            } else {
                // Show error message if name or description is empty
            }
        }
    }

    private fun setupViewModel() {
        val factory = TweetViewModelFactory()
        viewModel = ViewModelProvider(this, factory)[TweetViewModel::class.java]
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

