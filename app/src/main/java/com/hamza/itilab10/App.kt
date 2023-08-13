package com.hamza.itilab10

import android.app.Application
import com.hamza.itilab10.utils.MySharedPreferences

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MySharedPreferences.init(this)
    }
}