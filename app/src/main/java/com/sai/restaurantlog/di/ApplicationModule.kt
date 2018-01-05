package com.sai.restaurantlog.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by sai on 1/5/18.
 */

@Module class ApplicationModule(private val logApplication: LogApplication) {

    @Provides @Singleton fun provideApplication(): Application {
        return logApplication
    }
}