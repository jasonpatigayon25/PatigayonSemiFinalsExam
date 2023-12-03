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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tweet, parent, false)
        return TweetViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        holder.bind(tweets[position])
    }

    override fun getItemCount(): Int = tweets.size

    fun updateData(newTweets: List<Tweet>) {
        tweets = newTweets
        notifyDataSetChanged()
    }
    fun submitList(newTweets: List<Tweet>) {
        tweets = newTweets
        notifyDataSetChanged()
    }

    inner class TweetViewHolder(itemView: View, val onClick: (Tweet) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val tweetTextView: TextView = itemView.findViewById(R.id.tweetText)
        // Assume there are additional TextViews for name and timestamp if needed
        private val tweetNameTextView: TextView = itemView.findViewById(R.id.tweetName)
        private val tweetTimestampTextView: TextView = itemView.findViewById(R.id.tweetTimestamp)

        fun bind(tweet: Tweet) {
            tweetTextView.text = tweet.description
            tweetNameTextView.text = tweet.name
            tweetTimestampTextView.text = tweet.timestamp.toString()
            itemView.setOnClickListener { onClick(tweet) }
        }
    }

}