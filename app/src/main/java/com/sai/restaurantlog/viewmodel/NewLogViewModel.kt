package com.sai.restaurantlog.viewmodel

import android.arch.lifecycle.ViewModel
import com.sai.restaurantlog.data.repository.ILogRepository
import com.sai.restaurantlog.data.room.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by sai on 1/5/18.
 */

class NewLogViewModel constructor(private val logRepository: ILogRepository):
        ViewModel() {

    fun addNewLogItem(log: Log) {
        logRepository.createLog(log)
    }
}