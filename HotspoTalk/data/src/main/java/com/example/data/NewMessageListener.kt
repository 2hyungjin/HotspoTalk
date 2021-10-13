package com.example.data

import android.util.Log
import io.socket.emitter.Emitter
import org.json.JSONObject
import kotlin.properties.Delegates

class NewMessageListener() : Emitter.Listener {
    var eventHandler: ((String) -> Unit)? = null

    override fun call(vararg args: Any?) {
        val msg=args[0].toString()
        onMessageReceived(msg)
        Log.d("socket",msg)
    }

    private fun onMessageReceived(message: String) {
        eventHandler?.invoke(message)
    }
}