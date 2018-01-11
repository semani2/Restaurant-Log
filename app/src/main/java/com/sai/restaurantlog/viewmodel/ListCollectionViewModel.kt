package com.sai.restaurantlog.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.sai.restaurantlog.data.repository.ILogRepository
import com.sai.restaurantlog.data.room.Log

/**
 * Created by sai on 1/10/18.
 */

class ListCollectionViewModel constructor(private val logRepository: ILogRepository):
        ViewModel() {

    fun getListItems(): LiveData<List<Log>> {
        return logRepository.getAllLogs()
    }

    fun deleteLogItem(log: Log) {
        logRepository.deleteLog(log)
    }
}