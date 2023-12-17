package dev.no.room113.utils

import android.app.Application
import dev.no.room113.utils.preferences.Preferences
import dev.thuan.utils.alarm.AlarmUtils
import timber.log.Timber
import java.util.Timer

object UtilsCenter {
    private lateinit var application: Application
    private lateinit var activityLifecycleCenter: ActivityLifecycleCenter
    fun init(application: Application) {
        if (::application.isInitialized) {
            return
        }
        this.application = application
        initUtilsPlugin()
        initTimer()
        initPref(application)
        activityLifecycleCenter = ActivityLifecycleCenter()
        UtilsCenter.application.registerActivityLifecycleCallbacks(activityLifecycleCenter)
    }

    private fun initPref(application: Application) {
        Preferences.init(application)
    }

    private fun initTimer() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    fun getApp(): Application {
        if (!::application.isInitialized) {
            throw Exception("You must call Utils.init() before using Utils.getApp()")
        }

        return application
    }


    fun initUtilsPlugin() {
        AlarmUtils(application)
    }

    fun register(utilsPlugin: UtilsPlugin) {
        activityLifecycleCenter.utilsPluginList.add(utilsPlugin)
    }

    fun unregister(utilsPlugin: UtilsPlugin) {
        activityLifecycleCenter.utilsPluginList.remove(utilsPlugin)
    }
}
