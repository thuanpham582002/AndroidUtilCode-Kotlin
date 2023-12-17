package dev.no.room113.utils.notify.internal.utils

import androidx.annotation.IntDef
import dev.no.room113.utils.notify.Notify

/**
 * Denotes features which are considered experimental and are subject to change without notice.
 */
annotation class Experimental

@DslMarker
annotation class NotifyScopeMarker

@Retention(AnnotationRetention.SOURCE)
@IntDef(
    Notify.IMPORTANCE_MIN,
    Notify.IMPORTANCE_LOW,
    Notify.IMPORTANCE_NORMAL,
    Notify.IMPORTANCE_HIGH,
    Notify.IMPORTANCE_MAX,
)
annotation class NotifyImportance
