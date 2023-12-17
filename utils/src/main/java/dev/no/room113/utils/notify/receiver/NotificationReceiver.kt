package dev.no.room113.utils.notify.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import dev.no.room113.app.notify.NotificationFactory

/**
 * Created by KO Huyn on 11/08/2023.
 */
class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            dev.no.room113.app.notify.NotificationFactory.buildNotifyReminder(context)
            Log.d("MainActivity", "Build notify reminder")
        }
    }
}
