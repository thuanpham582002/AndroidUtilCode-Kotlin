package dev.no.room113.utils

import android.app.Activity
import android.app.Application
import android.os.Bundle

class ActivityLifecycleCenter : Application.ActivityLifecycleCallbacks {
    internal val utilsPluginList = mutableListOf<UtilsPlugin>()

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        utilsPluginList.forEach {
            it.onActivityCreated(activity, savedInstanceState)
        }
    }

    override fun onActivityStarted(activity: Activity) {
        utilsPluginList.forEach {
            it.onActivityStarted(activity)
        }
    }

    override fun onActivityResumed(activity: Activity) {
        utilsPluginList.forEach {
            it.onActivityResumed(activity)
        }
    }

    override fun onActivityPaused(activity: Activity) {
        utilsPluginList.forEach {
            it.onActivityPaused(activity)
        }
    }

    override fun onActivityStopped(activity: Activity) {
        utilsPluginList.forEach {
            it.onActivityStopped(activity)
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        utilsPluginList.forEach {
            it.onActivitySaveInstanceState(activity, outState)
        }
    }

    override fun onActivityDestroyed(activity: Activity) {
        utilsPluginList.forEach {
            it.onActivityDestroyed(activity)
        }
    }
}
