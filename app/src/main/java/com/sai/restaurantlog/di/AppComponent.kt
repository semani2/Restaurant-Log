package com.sai.restaurantlog.di

import android.app.Application
import dagger.Component
import javax.inject.Singleton

/**
 * Created by sai on 1/5/18.
 */

@Component(modules = arrayOf(ApplicationModule::class, RoomModule::class))
@Singleton interface AppComponent {

    fun application(): Application
}