package dev.no.room113.app.ui.bluetooth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.ramcosta.composedestinations.annotation.Destination
import dev.no.room113.utils.bluetooth.BlueToothDeviceWrapper

@Composable
@Destination
fun BlueTooth() {
    var blueToothDeviceWrappers by remember {
        mutableStateOf<List<BlueToothDeviceWrapper>>(emptyList())
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Turn on Bluetooth")
        }

        Button(onClick = { /*TODO*/ }) {
            Text(text = "Turn off Bluetooth")
        }

        Button(onClick = { /*TODO*/ }) {
            Text(text = "Get All Bonded Devices")
        }

        Button(onClick = { /*TODO*/ }) {
            Text(text = "Disconnect All Devices")
        }

        Button(onClick = { /*TODO*/ }) {
            Text(text = "Get All Connected Devices")
        }
    }
}

@Composable
fun BlueToothDetail() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

    }
}

@Composable
fun BlueToothDevice(list: List<BlueToothDeviceWrapper>) {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

        }
    }
}

@Composable
fun BlueToothDeviceItem() {
    var expanded by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

    }
}

@Composable
fun BlueToothUtilityDropDownMenu(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onDismissRequest: () -> Unit,
) {

    DropdownMenu(expanded = expanded, onDismissRequest = {
        onExpandedChange(false)
        onDismissRequest()
    }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // TODO: 12/5/2023
        }
    }

}