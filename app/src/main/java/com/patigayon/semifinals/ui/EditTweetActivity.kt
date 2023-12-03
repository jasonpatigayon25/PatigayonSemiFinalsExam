package com.patigayon.semifinals.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.patigayon.semifinals.R
import com.patigayon.semifinals.model.Tweet
import com.patigayon.semifinals.viewmodel.TweetViewModel

class EditTweetActivity : AppCompatActivity() {
    private lateinit var viewModel: TweetViewModel
    private lateinit var editTweetName: EditText
    private lateinit var editTweetDescription: EditText
    private lateinit var buttonSubmitTweet: Button
    private var tweetId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_or_edit_tweet)

        tweetId = intent.getStringExtra("TWEET_ID")
        setupUI()
        setupViewModel()
    }

    private fun setupUI() {
        editTweetName = findViewById(R.id.editTweetName)
        editTweetDescription = findViewById(R.id.editTweetDescription)
        buttonSubmitTweet = findViewById(R.id.buttonSubmitTweet)

        buttonSubmitTweet.setOnClickListener { updateTweet() }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[TweetViewModel::class.java]
        tweetId?.let {
            viewModel.getTweetDetail(it).observe(this) { tweet ->
                displayTweetData(tweet)
            }
        }
    }

    private fun displayTweetData(tweet: Tweet?) {
        tweet?.let {
            editTweetName.setText(it.name)
            editTweetDescription.setText(it.description)
        }
    }

    private fun updateTweet() {
        val name = editTweetName.text.toString()
        val description = editTweetDescription.text.toString()

        if (name.isNotEmpty() && description.isNotEmpty()) {
            val updatedTweet = Tweet(tweetId ?: "", name, description)
            viewModel.updateTweet(updatedTweet)
            finish() // Close Activity after update
        } else {
            // Show an error message if name or description is empty
        }
    }
}
