package dev.no.room113.app.notify.factory

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import dev.no.room113.app.MainActivity
import dev.no.room113.app.R
import dev.no.room113.utils.notify.Notify

/**
 * Created by KO Huyn on 11/08/2023.
 */
object NotificationFactory {
    fun buildNotifyReminder(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val clickableIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.FLAG_MUTABLE
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            },
        )
        Notify.with(context)
            .asCustomView {
                val remoteView = RemoteViews(
                    context.packageName,
                    R.layout.document_file_recents
                )
                remoteView.setOnClickPendingIntent(R.id.ivNotifyActionPlus, clickableIntent)
                this.remoteView = remoteView
            }
            .stackable { this.key = "1111" }
            .show()
    }
}
