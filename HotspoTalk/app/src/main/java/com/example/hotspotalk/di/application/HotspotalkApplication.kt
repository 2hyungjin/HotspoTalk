package com.example.hotspotalk.di.application

import android.app.Application
import android.util.Log
import com.example.data.NewMessageListener
import dagger.hilt.android.HiltAndroidApp
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.client.SocketIOException
import org.json.JSONObject

@HiltAndroidApp
class HotspotalkApplication : Application() {
    companion object {
        private lateinit var socket: Socket
        fun getSocket() = socket
    }

    override fun onCreate() {
        super.onCreate()
//        try {
//            socket = IO.socket("http://10.80.161.222:3000")
//            Log.d("SOCKET",socket.connect().toString())
//            socket.emit("test",JSONObject("msg:asdf"))
//            socket.on(Socket.EVENT_CONNECT, NewMessageListener())
//            Log.d("SOCKET", "connected " + socket.connected().toString())
//            Log.d("SOCKET", "isActive " + socket.isActive.toString())
//        } catch (e: SocketIOException) {
//            e.printStackTrace()
//        }
    }
}