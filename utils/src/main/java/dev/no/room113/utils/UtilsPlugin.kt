package dev.no.room113.utils

import android.app.Activity
import android.app.Application
import android.os.Bundle

interface UtilsPlugin : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        // TODO: 12/1/2023
    }

    override fun onActivityStarted(activity: Activity) {
        // TODO: 12/1/2023
    }

    override fun onActivityResumed(activity: Activity) {
        // TODO: 12/1/2023
    }

    override fun onActivityPaused(activity: Activity) {
        // TODO: 12/1/2023
    }

    override fun onActivityStopped(activity: Activity) {
        // TODO: 12/1/2023
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        // TODO: 12/1/2023
    }

    override fun onActivityDestroyed(activity: Activity) {
        // TODO: 12/1/2023
    }
}
