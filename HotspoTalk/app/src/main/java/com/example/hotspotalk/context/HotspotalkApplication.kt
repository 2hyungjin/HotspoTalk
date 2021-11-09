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
        val socket: Socket by lazy {
            IO.socket("http://ec2-18-219-111-210.us-east-2.compute.amazonaws.com:4000")
        }
        val newMessageListener: NewMessageListener = NewMessageListener()

        fun connectSocket() {
            socket.connect()
            socket.on("message", newMessageListener)
        }

    }

    override fun onCreate() {
        super.onCreate()
        Preference.init(applicationContext)
    }
}