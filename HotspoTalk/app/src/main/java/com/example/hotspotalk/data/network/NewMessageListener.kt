package com.example.hotspotalk.data.network

import android.util.Log
import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.entity.response.MessageResponse
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import io.socket.emitter.Emitter
import org.json.JSONObject

class NewMessageListener : Emitter.Listener {
    var eventHandler: ((MessageResponse) -> Unit)? = null

    override fun call(vararg args: Any?) {
        val message = args[0] as JSONObject

        val messageResponse = MessageResponse(
            message.getString("type"),
            message.getString("content"),
            message.getInt("roomID"),
            message.getString("nickname"),
            message.getString("timestamp"),
            message.getInt("messageID")
        )

        Log.d("NewMessageListener", "call: ${messageResponse.type}")
        onMessageReceived(messageResponse)
    }

    private fun onMessageReceived(messageResponse: MessageResponse) {
        eventHandler?.invoke(messageResponse)
    }
}