package com.sai.restaurantlog.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by sai on 1/5/18.
 */

@Database(entities = arrayOf(Log::class), version = 1)
abstract class LogDatabase: RoomDatabase() {

    abstract fun logDao(): LogDao
}