package com.example.data

import android.util.Log
import io.socket.emitter.Emitter
import org.json.JSONObject

class NewMessageListener : Emitter.Listener {
    override fun call(vararg args: Any?) {
        val result = JSONObject(args[0].toString())
        Log.d("SOCKET",args.toString())
    }
}