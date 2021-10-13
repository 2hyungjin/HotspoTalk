package com.example.hotspotalk.di.application

import android.app.Application
import com.example.data.NewMessageListener
import dagger.hilt.android.HiltAndroidApp
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject

@HiltAndroidApp
class HotspotalkApplication : Application() {
    companion object {
        val socket: Socket by lazy {
            IO.socket("http://10.80.161.222:3000")
        }
        val newMessageListener: NewMessageListener = NewMessageListener()

        fun connectSocket() {
            socket.connect()
            socket.on("test", newMessageListener)
            socket.emit("test", "msg")
        }
    }

    override fun onCreate() {
        super.onCreate()
    }

}