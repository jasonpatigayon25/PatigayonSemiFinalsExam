package com.patigayon.semifinals.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.patigayon.semifinals.databinding.ItemTweetBinding
import com.patigayon.semifinals.model.Tweet
import com.patigayon.semifinals.constants.Constants
import com.patigayon.semifinals.ui.TweetDetailActivity

class TweetAdapter(
    private val activity: Activity,
    private val tweetList: List<Tweet>,
): RecyclerView.Adapter<TweetAdapter.TweetViewHolder>() {

    class TweetViewHolder(
        private val binding: ItemTweetBinding,
        private val activity: Activity
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(tweet: Tweet) {

            binding.name.text = tweet.name
            binding.description.text = tweet.description

            binding.root.setOnClickListener {
                val intent = Intent(activity, TweetDetailActivity::class.java)
                intent.putExtra(Constants.PARAM_ID, tweet.id)
                activity.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTweetBinding.inflate(
            inflater,
            parent,
            false,
        )
        return TweetViewHolder(binding, activity)
    }

    override fun getItemCount() = tweetList.size

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        holder.bind(tweetList[position])
    }
}