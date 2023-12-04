package com.patigayon.semifinals.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.patigayon.semifinals.R
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

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.imageViewOptions.setOnClickListener { view ->
            showOverflowMenu(view)
        }

        getTweet()
    }

    private fun getTweet() {
        val tweetId = intent.getStringExtra(Constants.PARAM_ID) ?: return
        ServiceBuilder.tweetApiService.getTweet(tweetId).enqueue(object : Callback<Tweet> {
            override fun onResponse(call: Call<Tweet>, response: Response<Tweet>) {
                if (response.isSuccessful) {
                    response.body()?.let { tweet ->
                        binding.textViewTweetName.text = tweet.name
                        binding.textViewTweetDescription.text = tweet.description
                        binding.progressBar.visibility = View.GONE
                    }
                } else {
                    showError("Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Tweet>, t: Throwable) {
                showError("Failure: ${t.message}")
            }
        })
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
    private fun showOverflowMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.menu_tweet_detail, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_edit -> {
                    val intent = Intent(this, EditTweetActivity::class.java).apply {
                        putExtra(Constants.PARAM_ID, intent.getStringExtra(Constants.PARAM_ID))
                    }
                    startActivity(intent)
                    true
                }
                R.id.action_delete -> {
                    val intent = Intent(this, DeleteTweetActivity::class.java).apply {
                        putExtra(Constants.PARAM_ID, intent.getStringExtra(Constants.PARAM_ID))
                    }
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
}
