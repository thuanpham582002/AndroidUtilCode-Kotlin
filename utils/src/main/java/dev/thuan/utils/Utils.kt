package dev.thuan.utils

import android.app.Application

object Utils {
    private lateinit var application: Application


    fun init(application: Application) {
        this.application = application
    }

    fun getApp(): Application {
        if (!::application.isInitialized) {
            throw Exception("You must call Utils.init() before using Utils.getApp()")
        }
        return application
    }
}