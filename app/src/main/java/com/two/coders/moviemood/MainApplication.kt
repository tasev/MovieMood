package com.two.coders.moviemood

import android.app.Application
import com.two.coders.moviemood.utils.SharedPref
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPref.init(this)
    }
}