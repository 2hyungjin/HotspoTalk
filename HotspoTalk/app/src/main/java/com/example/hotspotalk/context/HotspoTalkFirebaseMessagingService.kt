package com.example.hotspotalk.context

import com.example.hotspotalk.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.hotspotalk.view.activity.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class HotspoTalkFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }
}