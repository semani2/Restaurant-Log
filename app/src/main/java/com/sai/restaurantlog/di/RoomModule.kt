package com.sai.restaurantlog.di

import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.sai.restaurantlog.data.repository.ILogRepository
import com.sai.restaurantlog.data.repository.LogRepository
import com.sai.restaurantlog.data.room.LogDao
import com.sai.restaurantlog.data.room.LogDatabase
import com.sai.restaurantlog.viewmodel.CustomViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by sai on 1/5/18.
 */

@Module class RoomModule(application: LogApplication) {

    private val logDatabase: LogDatabase = Room.databaseBuilder(
            application,
            LogDatabase::class.java,
            "Log.db"
    ).build()

    @Provides @Singleton fun provideLogRepository(logDao: LogDao): ILogRepository {
        return LogRepository(logDao)
    }

    @Provides @Singleton fun provideLogDao(logDatabase: LogDatabase): LogDao {
        return logDatabase.logDao()
    }

    @Provides @Singleton fun provideLogDatabase(): LogDatabase {
        return logDatabase
    }

    @Provides @Singleton fun provideViewModelFactory(logRepository: ILogRepository): ViewModelProvider.Factory {
        return CustomViewModelFactory(logRepository)
    }

}
