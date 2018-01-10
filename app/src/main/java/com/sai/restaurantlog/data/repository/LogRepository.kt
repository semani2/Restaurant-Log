package com.sai.restaurantlog.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.sai.restaurantlog.data.room.Log
import com.sai.restaurantlog.data.room.LogDao
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by sai on 1/5/18.
 */

@Singleton class LogRepository @Inject constructor(private val logDao: LogDao) : ILogRepository {

    private val allCompositeDisposable: MutableList<Disposable> = arrayListOf()

    override fun getAllLogs(): LiveData<List<Log>> {
        val mutableLiveData = MutableLiveData<List<Log>>()
        val disposable = logDao.getAllLogs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ logList -> mutableLiveData.value = logList}, {
                    t: Throwable? -> t!!.printStackTrace()
                })

        allCompositeDisposable.add(disposable)
        return mutableLiveData
    }

    override fun getLog(id: Long): LiveData<Log> {
        val mutableLiveData = MutableLiveData<Log>()
        val disposable = logDao.getLogById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ log -> mutableLiveData.value = log}, {
                    t: Throwable? -> t!!.printStackTrace()
                })

        allCompositeDisposable.add(disposable)
        return mutableLiveData
    }

    override fun createLog(log: Log){
        Completable.fromAction({logDao.insertLog(log)})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ android.util.Log.d("LogRepository", "Log added to database") })
    }

    override fun deleteLog(log: Log) {
       return logDao.deleteLog(log)
    }

}