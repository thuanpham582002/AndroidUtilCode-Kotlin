package dev.no.room113.app.ui.home

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.no.room113.app.ui.destinations.AlarmDestination

@RootNavGraph(start = true) // sets this as the start destination of the default nav graph
@Composable
@Destination
fun Home(
    navigator: DestinationsNavigator
) {
    Button(
        onClick = {
            navigator.navigate(AlarmDestination)
        },
        modifier = Modifier.wrapContentSize()
    ) {
        Text(text = "Alarm")
    }
}