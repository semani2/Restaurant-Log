package com.sai.restaurantlog.adapters

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sai.restaurantlog.R
import com.sai.restaurantlog.data.room.Log

/**
 * Created by sai on 1/10/18.
 */

class LogAdapter(private var activity: AppCompatActivity, private var logList: MutableList<Log>)
    : RecyclerView.Adapter<LogAdapter.LogViewHolder>() {

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        val log = logList[position]

        holder.restaurantNameTextView.text = log.restaurantName
        holder.restaurantReviewTextView.text = log.review
        holder.restaurantRatingTextView.text = log.rating.toString()
    }

    override fun getItemCount(): Int {
        return logList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): LogViewHolder {
        val v: View = activity.layoutInflater.inflate(R.layout.item_log, parent,false)
        return LogViewHolder(v)
    }


    class LogViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var restaurantNameTextView: TextView
        var restaurantReviewTextView: TextView
        var restaurantRatingTextView: TextView

        init {
            restaurantNameTextView = itemView.findViewById(R.id.restaurant_name_text_view)
            restaurantReviewTextView = itemView.findViewById(R.id.restaurant_review_text_view)
            restaurantRatingTextView = itemView.findViewById(R.id.rating_text_view)
        }
    }
}