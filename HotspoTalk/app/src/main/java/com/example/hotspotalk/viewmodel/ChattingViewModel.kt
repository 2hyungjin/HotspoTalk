package com.example.hotspotalk.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotspotalk.context.HotspotalkApplication
import com.example.hotspotalk.data.entity.Message
import com.example.hotspotalk.data.entity.request.MessageRequest
import com.example.hotspotalk.data.entity.response.MemberInfo
import com.example.hotspotalk.data.entity.response.MessageResponse
import com.example.hotspotalk.data.repository.ChattingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChattingViewModel @Inject constructor(private val messageRepository: ChattingRepository) :
    ViewModel() {

    val chatList = MutableLiveData<List<Message>>()
    private val _chatList = mutableListOf<Message>()

    private val _chat = MutableLiveData<Message>()
    val chat: LiveData<Message> = _chat

    val memberList = MutableLiveData<List<MemberInfo>>()
    private val _memberList = mutableListOf<MemberInfo>()

    val getMessageFailure = MutableLiveData<String>()
    val getMembersFailure = MutableLiveData<String>()

    val isLoading = MutableLiveData<Boolean>(false)

    var job: Job? = null

    init {
        HotspotalkApplication.newMessageListener.eventHandler = { newMessageEventHandle(it) }
    }

    private fun newMessageEventHandle(message: MessageResponse) {
        _chat.postValue(message.toMessage())
    }


    fun enterChatting(roomId: Int) {
        Log.d("ChattingViewModel", "enterChatting: $roomId")
        getMessages(roomId, 10, 0)
        getMembers(roomId)
        HotspotalkApplication.socket.emit("in", roomId)
    }

    private fun getMessages(roomId: Int, count: Int, start: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)
            messageRepository.getMessages(roomId, count, start).let { result ->
                if (result.isSuccessful) {
                    Log.d("chatting", result.body().toString())
                    result.body()!!.map { it.toMessage() }
                        .let {
                            _chatList.addAll(it)
                            chatList.postValue(_chatList)
                        }
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

    private fun getMembers(roomId: Int) {
        isLoading.postValue(true)
        job = viewModelScope.launch {
            messageRepository.getMembers(roomId).let { result ->
                Log.d("chatting", result.body().toString())
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

    fun outChatting(roomId: Int) {
        viewModelScope.launch {
            messageRepository.outChattingRoom(roomId)
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