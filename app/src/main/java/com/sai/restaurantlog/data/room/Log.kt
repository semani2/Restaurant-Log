package com.sai.restaurantlog.data.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by sai on 1/5/18.
 */

@Entity
data class Log(
       @PrimaryKey(autoGenerate = true) val id: Long,
       var restaurantName: String,
       var review: String,
       var rating: Float
)