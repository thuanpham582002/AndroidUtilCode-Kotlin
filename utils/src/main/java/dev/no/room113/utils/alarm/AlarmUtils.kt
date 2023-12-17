package dev.thuan.utils.alarm

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import androidx.core.app.AlarmManagerCompat

class AlarmUtils internal constructor(private val context: Context) {
    private var alarmMgr: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: AlarmUtils
            private set
    }

    init {
        instance = this
    }

    /**
     * @RequiresPermission(android.Manifest.permission.SCHEDULE_EXACT_ALARM) on Android 12  and above
     * @RequiresPermission(android.Manifest.permission.USE_EXACT_ALARM) on Android 13 and above
     * @param triggerTimeMillis The time in milliseconds that the alarm should go off, using the
     * appropriate clock (depending on the alarm type).
     * @param showIntent Is PendingIntent.getActivity(), it will open when alarm on StatusBar
     * @param pendingIntent Is PendingIntent.getBroadcast(), it will send an broadcast when alarm on
     */
    fun setAlarm(triggerTimeMillis: Long, showIntent: PendingIntent, pendingIntent: PendingIntent) {
        AlarmManagerCompat.setAlarmClock(alarmMgr, triggerTimeMillis, showIntent, pendingIntent)
    }
}
