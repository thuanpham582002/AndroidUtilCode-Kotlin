package dev.no.room113.app

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.pm.PackageManager.GET_ACTIVITIES
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.DestinationsNavHost
import dev.no.room113.app.ui.NavGraphs
import dev.no.room113.app.ui.theme.AndroidUtilsCodeKotlinTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activities = getActivitiesFromManifest(packageName)

        // Print the names of the activities to the log
        for (activity in activities) {
            Timber.d(activity.name + " " + activity.packageName)
        }
        setContent {
            AndroidUtilsCodeKotlinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }

    private fun getActivitiesFromManifest(
        packageName: String,
    ): List<ActivityInfo> {
        val packageManager: PackageManager = packageManager
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        Timber.d("packageName: $packageName")
        // Retrieve the list of activities that can handle the main intent
        val resolveInfos: List<ResolveInfo> =
            packageManager.queryIntentActivities(intent, GET_ACTIVITIES)

        val activities: MutableList<ActivityInfo> = mutableListOf()

        // Iterate through the ResolveInfo objects and extract ActivityInfo
        for (resolveInfo in resolveInfos) {
            if (resolveInfo.activityInfo.packageName != packageName) {
                continue
            }
            activities.add(resolveInfo.activityInfo)
        }

        return activities
    }
}
