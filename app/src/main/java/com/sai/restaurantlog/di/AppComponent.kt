package com.sai.restaurantlog.di

import android.app.Application
import com.sai.restaurantlog.add_log.AddLogActivity
import com.sai.restaurantlog.main.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by sai on 1/5/18.
 */

@Component(modules = arrayOf(ApplicationModule::class, RoomModule::class))
@Singleton interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: AddLogActivity)

    fun application(): Application
}