package com.sai.restaurantlog.di

import android.app.Application
import com.facebook.stetho.Stetho

/**
 * Created by sai on 1/5/18.
 */

class LogApplication: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
        initializeStetho()
    }

    private fun initializeStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
                .applicationModule(ApplicationModule(this))
                .roomModule(RoomModule(this))
                .build()
    }

    fun getApplicationComponent(): AppComponent {
        return appComponent
    }
}