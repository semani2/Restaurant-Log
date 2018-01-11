package com.sai.restaurantlog.data.repository

import android.arch.lifecycle.LiveData
import com.sai.restaurantlog.data.room.Log
import io.reactivex.Single

/**
 * Created by sai on 1/5/18.
 */

interface ILogRepository {

    fun getAllLogs(): LiveData<List<Log>>

    fun getLog(id: Long): LiveData<Log>

    fun createLog(log: Log)

    fun deleteLog(log: Log)
}