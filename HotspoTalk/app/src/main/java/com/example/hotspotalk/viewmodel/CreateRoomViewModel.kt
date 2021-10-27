package com.example.hotspotalk.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotspotalk.data.entity.repuest.CreateRoom
import com.example.hotspotalk.data.repository.RoomsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateRoomViewModel @Inject constructor(
    private val roomsRepository: RoomsRepository
): ViewModel() {
    private val _isFailure = MutableLiveData<String>()
    val isFailure: LiveData<String> = _isFailure

    private val _isSuccess = MutableLiveData<String>()
    val isSuccess: LiveData<String> = _isSuccess

    val memberLimit = MutableLiveData<String>()
    val roomName = MutableLiveData<String>()
    val roomPassword = MutableLiveData<String>()
    val areaType = MutableLiveData<Int>()
    val areaDetail = MutableLiveData(0)
    val latitude = MutableLiveData<Double>()
    val longitude = MutableLiveData<Double>()

    fun createRoom() {
        val roomName = roomName.value ?: ""
        val roomPassword = roomPassword.value ?: ""
        val memberLimit = memberLimit.value ?: "0"
        val areaType = areaType.value ?: 0
        val areaDetail = areaDetail.value ?: 0
        val latitude = latitude.value ?: 0.0
        val longitude = longitude.value ?: 0.0

        viewModelScope.launch {
            if (roomName.isNotEmpty() && memberLimit.toInt() > 0 && latitude != 0.0 && longitude != 0.0 && areaDetail > 0.0) {
                val createRoom = CreateRoom(roomName, roomPassword, memberLimit.toInt(), latitude, longitude, areaType, areaDetail)
                val msgResponse = roomsRepository.postCreateRoom(createRoom)
                when {
                    msgResponse.isSuccessful -> {
                        _isSuccess.value = msgResponse.body()?.msg.toString()
                    }
                    msgResponse.code() in 400..499 -> {
                        _isFailure.value = msgResponse.message()
                    }
                }
            } else {
                _isFailure.value = "빈 칸이 없는지 확인해주세요."
            }
        }
    }
}