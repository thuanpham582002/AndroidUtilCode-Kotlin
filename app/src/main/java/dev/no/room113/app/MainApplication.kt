package dev.no.room113.app

import android.app.Application
import dev.no.room113.utils.UtilsCenter

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        UtilsCenter.init(this)
    }
}
