package com.example.hotspotalk.view.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object Preference {
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("token", MODE_PRIVATE)
    }

    var token: String
        get() = sharedPreferences.getString("token", "")?: ""
        set(value) = sharedPreferences.edit().putString("token", value).apply()
}