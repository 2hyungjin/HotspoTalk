package com.example.hotspotalk.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.hotspotalk.R
import com.example.hotspotalk.di.application.HotspotalkApplication
import com.example.hotspotalk.viewmodel.ChattingViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel:ChattingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        HotspotalkApplication.connectSocket()
        viewModel.initialViewModel()
        viewModel.chat.observe(this,{
            Log.d("MainActivity","message is arrived $it")
        })
    }
}