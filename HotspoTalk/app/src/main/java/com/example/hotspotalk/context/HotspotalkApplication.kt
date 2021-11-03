package com.example.hotspotalk.context

import android.app.Application
import com.example.hotspotalk.data.network.NewMessageListener
import com.example.hotspotalk.view.util.Preference
import dagger.hilt.android.HiltAndroidApp
import io.socket.client.IO
import io.socket.client.Socket

@HiltAndroidApp
class HotspotalkApplication : Application() {
    companion object {
        private val socket: Socket by lazy {
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
        Preference.init(applicationContext)
    }

}