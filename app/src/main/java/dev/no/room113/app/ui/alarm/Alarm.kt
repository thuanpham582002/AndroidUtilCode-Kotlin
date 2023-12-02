package dev.no.room113.app.ui.alarm

import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.no.room113.app.MainActivity
import dev.no.room113.app.receiver.AlarmReceiver
import dev.thuan.utils.alarm.AlarmUtils
import timber.log.Timber
import java.util.Calendar

@Destination
@Composable
fun Alarm(
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val hour = remember { mutableStateOf(calendar.get(Calendar.HOUR_OF_DAY)) }
    val minute = remember { mutableStateOf(calendar.get(Calendar.MINUTE)) }

    LaunchedEffect(hour, minute) {
        // TODO: 12/2/2023
    }

    Button(
        onClick = {
            TimePickerDialog(context, { timePicker, selectedHour, selectedMinute ->
                hour.value = selectedHour
                minute.value = selectedMinute
                if (selectedHour < calendar.get(Calendar.HOUR_OF_DAY) ||
                    (selectedHour == calendar.get(Calendar.HOUR_OF_DAY) &&
                            selectedMinute < calendar.get(Calendar.MINUTE))
                ) {
                    calendar.add(Calendar.DAY_OF_MONTH, 1)
                    calendar.set(Calendar.HOUR_OF_DAY, selectedHour)
                    calendar.set(Calendar.MINUTE, selectedMinute)
                } else {
                    calendar.set(Calendar.HOUR_OF_DAY, selectedHour)
                    calendar.set(Calendar.MINUTE, selectedMinute)
                }
                AlarmUtils.instance.setAlarm(
                    calendar.timeInMillis,
                    PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE),
                    PendingIntent.getBroadcast(
                        context,
                        0,
                        Intent(context, AlarmReceiver::class.java),
                        PendingIntent.FLAG_IMMUTABLE
                    )
                )
                Timber.d("Selected time: $selectedHour:$selectedMinute")
            }, hour.value, minute.value, false).show()
        },
        modifier = Modifier.wrapContentSize()
    ) {
        Text(text = "Set Alarm")
    }
}
