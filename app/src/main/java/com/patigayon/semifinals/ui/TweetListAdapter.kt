package com.patigayon.semifinals.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.patigayon.semifinals.R
import com.patigayon.semifinals.model.Tweet

class TweetListAdapter(private val onClick: (Tweet) -> Unit) :
    RecyclerView.Adapter<TweetListAdapter.TweetViewHolder>() {

    var tweets: List<Tweet> = listOf()

    // Create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tweet, parent, false)
        return TweetViewHolder(view, onClick)
    }

    // Replace the contents of a view
    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        holder.bind(tweets[position])
    }

    // Return the size of your dataset
    override fun getItemCount(): Int = tweets.size

    // Update the list of tweets and notify the adapter of the change
    fun submitList(newTweets: List<Tweet>) {
        tweets = newTweets
        notifyDataSetChanged()
    }

    // Provide a reference to the type of views that you are using
    inner class TweetViewHolder(itemView: View, val onClick: (Tweet) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val tweetTextView: TextView = itemView.findViewById(R.id.tweetText)
        // Assume there are additional TextViews for name and timestamp if needed
        private val tweetNameTextView: TextView = itemView.findViewById(R.id.tweetName)
        private val tweetTimestampTextView: TextView = itemView.findViewById(R.id.tweetTimestamp)

        // Bind the data to the views
        fun bind(tweet: Tweet) {
            tweetTextView.text = tweet.description
            // Bind additional data if available
            tweetNameTextView.text = tweet.name
            tweetTimestampTextView.text = tweet.timestamp.toString() // Format timestamp if needed
            // Set an onClick listener to the entire itemView and pass the tweet object to the click handler
            itemView.setOnClickListener { onClick(tweet) }
        }
    }
}