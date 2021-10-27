package com.example.hotspotalk.data.network

import android.util.Log
import com.example.hotspotalk.data.entity.response.MessageResponse
import io.socket.emitter.Emitter

class NewMessageListener() : Emitter.Listener {
    var eventHandler: ((MessageResponse) -> Unit)? = null

    override fun call(vararg args: Any?) {
        val msg=args[0].toString()
        onMessageReceived(msg as MessageResponse )
        Log.d("socket",msg)
    }

    private fun onMessageReceived(message: MessageResponse) {
        eventHandler?.invoke(message)
    }
}