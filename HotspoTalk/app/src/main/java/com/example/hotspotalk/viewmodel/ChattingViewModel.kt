package com.example.hotspotalk.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotspotalk.context.HotspotalkApplication
import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.entity.MessageType
import com.example.hotspotalk.data.entity.repuest.MessageRequest
import com.example.hotspotalk.data.entity.response.MemberInfo
import com.example.hotspotalk.data.entity.response.MessageResponse
import com.example.hotspotalk.data.repository.ChattingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChattingViewModel @Inject constructor(val messageRepository: ChattingRepository) :
    ViewModel() {

    val chatList = MutableLiveData<List<Message>>()
    private val _chatList = mutableListOf<Message>()

    val memberList = MutableLiveData<List<MemberInfo>>()
    private val _memberList = mutableListOf<MemberInfo>()

    val getMessageFailure = MutableLiveData<String>()
    val getMembersFailure = MutableLiveData<String>()

    val isLoading = MutableLiveData<Boolean>(false)

    var job: Job? = null

    fun initialViewModel() {
        Log.d("ViewModel", "init")
        HotspotalkApplication.newMessageListener.eventHandler = { newMessageEventHandle(it) }
    }

    private fun newMessageEventHandle(message: MessageResponse) {
        _chatList.add(Message(message, MessageType.YOURS))
        chatList.postValue(_chatList)
    }

    fun enterChatting(roomId: Int) {
        getMessages(roomId)
        getMembers(roomId)
    }

    fun getMessages(roomId: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)
            messageRepository.getMessages(roomId).let { result ->
                if (result.isSuccessful) {
                    result.body()!!.map { Message(it, MessageType.YOURS) }
                        .let { _chatList.addAll(it) }
                } else {
                    getMessageFailure.postValue("메세지 로딩에 실패하였습니다.")
                }
                isLoading.postValue(false)
            }
        }
    }

    fun postMessage(message: MessageRequest) {
        job = viewModelScope.launch {
            messageRepository.postMessage(message)
        }
    }

    fun getMembers(roomId: Int) {
        isLoading.postValue(true)
        job = viewModelScope.launch {
            messageRepository.getMembers(roomId).let { result ->
                if (result.isSuccessful) {
                    _memberList.addAll(result.body()!!)
                    memberList.postValue(_memberList)
                } else {
                    getMembersFailure.postValue("유저 로딩에 실패")
                }
            }
            isLoading.postValue(false)
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