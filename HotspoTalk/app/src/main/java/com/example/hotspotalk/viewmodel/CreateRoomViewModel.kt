package com.example.hotspotalk.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateRoomViewModel: ViewModel() {
    private val _isFailure = MutableLiveData<String>()
    val isFailure: LiveData<String> = _isFailure

    private val _isSuccess = MutableLiveData<String>()
    val isSuccess: LiveData<String> = _isSuccess

    val chattingRoomName = MutableLiveData<String>()
    val totalNumber = MutableLiveData<String>()

    fun createRoom() {
        val chattingRoomName = chattingRoomName.value ?: ""
        val totalNumber = totalNumber.value ?: ""

        if (chattingRoomName.isNotEmpty() && totalNumber.isNotEmpty()) {
            try {
                _isSuccess.value = "success"
            } catch (e: Throwable) {
                // 실패
            }
        } else {
            _isFailure.value = "빈 칸이 없는지 확인해주세요."
        }
    }
}