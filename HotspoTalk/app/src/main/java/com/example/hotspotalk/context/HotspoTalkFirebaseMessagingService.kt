package com.example.hotspotalk.context

import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.hotspotalk.view.activity.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class HotspoTalkFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent =
            PendingIntent.getActivity(baseContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val channelId = "message"
        val channel = NotificationChannel(
            channelId, channelId,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            this.setShowBadge(true)

        }
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)


        message.notification?.let {
            Log.d("FCM", "body : ${it.body}")

            val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setContentText(it.body)
                .setSmallIcon(R.drawable.sym_def_app_icon)
                .setFullScreenIntent(pendingIntent, true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_MAX)
            notificationBuilder.priority = NotificationCompat.PRIORITY_HIGH

            notificationManager.notify(1, notificationBuilder.build())
        }
    }
}