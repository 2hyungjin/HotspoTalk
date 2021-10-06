package com.example.hotspotalk.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.data.NewMessageListener
import com.example.hotspotalk.R
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.client.SocketIOException

class MainActivity : AppCompatActivity() {
    lateinit var socket: io.socket.client.Socket
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            socket = IO.socket("http://10.80.161.222:3000")
        } catch (e: SocketIOException) {
            e.printStackTrace()
        }
        socket.connect()
        Log.d("SOCKET", "connected " + socket.connected().toString())
        Log.d("SOCKET", "isActive " + socket.isActive.toString())
        socket.on(Socket.EVENT_CONNECT, NewMessageListener())

    }
}