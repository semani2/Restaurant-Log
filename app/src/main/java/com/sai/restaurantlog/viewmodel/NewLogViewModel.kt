package com.sai.restaurantlog.viewmodel

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
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

    private val compositeDisposable = CompositeDisposable()

    fun addNewLogItem(log: Log) {
        val disposable = logRepository.createLog(log)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t -> if(t < -1) android.util.Log.d("NewLogViewModel",
                        "Error entering log into database")
                })

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}