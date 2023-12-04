package com.patigayon.semifinals.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.patigayon.semifinals.api.ServiceBuilder
import com.patigayon.semifinals.constants.Constants
import com.patigayon.semifinals.databinding.ActivityDeleteTweetBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteTweetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteTweetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteTweetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tweetId = intent.getStringExtra(Constants.PARAM_ID)

        binding.buttonConfirmDelete.setOnClickListener {
            if (tweetId != null) {
                deleteTweet(tweetId)
            }
        }

        binding.buttonCancelDelete.setOnClickListener {
            finish() // Close the activity without deleting
        }
    }

    private fun deleteTweet(tweetId: String) {
        ServiceBuilder.tweetApiService.deleteTweet(tweetId).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@DeleteTweetActivity, "Tweet deleted successfully", Toast.LENGTH_SHORT).show()
                    finish() // Close the activity after successful deletion
                } else {
                    showError("Error deleting tweet: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                showError("Failed to delete tweet: ${t.localizedMessage}")
            }
        })
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
