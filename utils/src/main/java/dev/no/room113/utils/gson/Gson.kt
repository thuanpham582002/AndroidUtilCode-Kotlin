package dev.no.room113.utils.gson

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

fun Any.toJSONString(): String {
    return Gson().toJson(this, Any::class.java)
}

fun Any.toJSONObject(): JSONObject {
    return JSONObject(Gson().toJson(this, Any::class.java))
}

fun <T> Gson.fromJson(jsonString: String): T =
    fromJson(jsonString, object : TypeToken<T>() {}.type)
