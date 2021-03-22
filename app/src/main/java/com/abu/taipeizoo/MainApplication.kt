package com.abu.taipeizoo

import android.app.Application
import android.content.Context

class MainApplication : Application() {
    companion object {
        private lateinit var instance: Application
        fun getContext(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}