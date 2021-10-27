package com.example.hotspotalk.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hotspotalk.context.HotspotalkApplication

class ChattingViewModel : ViewModel() {
    val chat = MutableLiveData<String>()

    fun initialViewModel(){
        Log.d("ViewModel", "init")
        val listener = HotspotalkApplication.newMessageListener.apply {
            eventHandler = {
                chat.postValue(it)
//                Log.d("ChattingViewModel", it.toString())
            }
        }
    }


}