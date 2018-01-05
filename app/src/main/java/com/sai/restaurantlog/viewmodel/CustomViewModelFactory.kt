package com.sai.restaurantlog.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.sai.restaurantlog.data.repository.ILogRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by sai on 1/5/18.
 */

@Singleton class CustomViewModelFactory @Inject constructor(private val repository: ILogRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(NewLogViewModel::class.java))
            NewLogViewModel(repository) as T
        else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}