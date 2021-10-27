package com.example.hotspotalk.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotspotalk.context.HotspotalkApplication
import com.example.hotspotalk.data.entity.repuest.MessageRequest
import com.example.hotspotalk.data.entity.response.MessageResponse
import com.example.hotspotalk.data.repository.MessageRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChattingViewModel @Inject constructor(val messageRepository: MessageRepository) :
    ViewModel() {
    val chatList = MutableLiveData<List<MessageResponse>>()
    private val _chatList = mutableListOf<MessageResponse>()
    var job: Job? = null
    fun initialViewModel() {
        Log.d("ViewModel", "init")
        HotspotalkApplication.newMessageListener.eventHandler = { newMessageEventHandle(it) }
    }

    private fun newMessageEventHandle(message: MessageResponse) {
        _chatList.add(message)
        chatList.postValue(_chatList)
    }

    fun getMessages(roomId: Int) {
        job = viewModelScope.launch {
            messageRepository.getMessages(roomId)
        }
    }

    fun postMessage(message: MessageRequest) {
        job = viewModelScope.launch {
            messageRepository.postMessage(message)
        }
    }

    override fun onCleared() {
        job = job?.let {
            it.cancel()
            null
        }
        super.onCleared()
    }


}