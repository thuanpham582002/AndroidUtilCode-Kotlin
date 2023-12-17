package dev.no.room113.utils.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothHeadset
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import dev.no.room113.utils.UtilsCenter
import timber.log.Timber

/**
 * https://developer.android.com/guide/topics/connectivity/bluetooth
 */


// todo migrate to  Context#createAttributionContext for get BluetoothAdapter
data class BlueToothDeviceWrapper(
    val device: BluetoothDevice,
    val isConnected: Boolean,
    val isBonded: Boolean,
    val name: String,
    val batteryLevel: Int,
    val blueToothAudioCodec: Int,
)

object BlueToothUtils {
    val bluetoothManager = UtilsCenter.getApp().getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    @SuppressLint("MissingPermission")
    fun isBluetoothHeadsetConnected(context: Context): Boolean {
        val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (mBluetoothAdapter == null) {
            Timber.e("BluetoothAdapter is null")
            return false
        }
        val isBluetoothHeadsetConnected =
            mBluetoothAdapter != null && mBluetoothAdapter.isEnabled &&
                    mBluetoothAdapter.getProfileConnectionState(BluetoothHeadset.HEADSET) == BluetoothHeadset.STATE_CONNECTED
        return isBluetoothHeadsetConnected
    }

    @SuppressLint("MissingPermission")
    fun getAllBondedDevices(): Set<BlueToothDeviceWrapper> {
        val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (mBluetoothAdapter == null) {
            Timber.e("BluetoothAdapter is null")
            return emptySet()
        }
        return mBluetoothAdapter.bondedDevices.map { it.toWrapper() }.toSet()
    }

    fun turnOnBluetooth() {
        // TODO: 12/3/2023
    }
}

@SuppressLint("MissingPermission")
fun BluetoothDevice.toWrapper(): BlueToothDeviceWrapper {
    return BlueToothDeviceWrapper(
        device = this,
        isConnected = this.isConnected(),
        isBonded = true,
        name = this.name,
        batteryLevel = this.batteryLevel(),
        blueToothAudioCodec = 0,
    )
}

fun BluetoothDevice.batteryLevel(): Int {
    return this.javaClass.getMethod("getBatteryLevel").invoke(this) as Int
}

fun BluetoothDevice.isBonded(): Boolean {
    return this.bondState == BluetoothDevice.BOND_BONDED
}

fun BluetoothDevice.isConnected(): Boolean {
    return this.javaClass.getMethod("isConnected").invoke(this) as Boolean
}
fun BluetoothDevice.removeBond() {
    try {
        javaClass.getMethod("removeBond").invoke(this)
    } catch (e: Exception) {
        Timber.e("Removing bond has been failed. " + e.message)
    }
}


fun BluetoothDevice.disconnect(context: Context) {
    val blueToothDevice = this
    val serviceListener: BluetoothProfile.ServiceListener = object :
        BluetoothProfile.ServiceListener {
        override fun onServiceDisconnected(profile: Int) {
            Timber.i("onServiceDisconnected: $profile")
        }

        @SuppressLint("DiscouragedPrivateApi", "PrivateApi")
        override fun onServiceConnected(profile: Int, proxy: BluetoothProfile) {
            Timber.i("onServiceConnected: $profile $proxy")
            val disconnect = BluetoothHeadset::class.java.getDeclaredMethod(
                "disconnect",
                BluetoothDevice::class.java,
            )
            disconnect.isAccessible = true
            disconnect.invoke(proxy, blueToothDevice)
            BluetoothAdapter.getDefaultAdapter().closeProfileProxy(profile, proxy)
        }
    }
    BluetoothAdapter.getDefaultAdapter()
        .getProfileProxy(context, serviceListener, BluetoothProfile.HEADSET)
}

