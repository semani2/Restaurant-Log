package com.sai.restaurantlog.data.repository

import android.arch.lifecycle.LiveData
import com.sai.restaurantlog.data.room.Log
import com.sai.restaurantlog.data.room.LogDao
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by sai on 1/5/18.
 */

@Singleton class LogRepository @Inject constructor(private val logDao: LogDao) : ILogRepository {

    override fun getAllLogs(): LiveData<List<Log>> {
        return logDao.getAllLogs()
    }

    override fun getLog(id: Long): LiveData<Log> {
       return logDao.getLogById(id)
    }

    override fun createLog(log: Log): Long {
        return logDao.insertLog(log)
    }

    override fun deleteLog(log: Log) {
       return logDao.deleteLog(log)
    }

}