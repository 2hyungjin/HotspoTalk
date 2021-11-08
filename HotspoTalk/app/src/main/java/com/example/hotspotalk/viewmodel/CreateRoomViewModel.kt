package com.example.hotspotalk.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotspotalk.data.entity.request.CreateRoom
import com.example.hotspotalk.data.repository.RoomsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateRoomViewModel @Inject constructor(
    private val roomsRepository: RoomsRepository
): ViewModel() {
    val isMapLoading = MutableLiveData(false)
    private val _isFailure = MutableLiveData<String>()
    val isFailure: LiveData<String> = _isFailure

    private val _isSuccess = MutableLiveData<String>()
    val isSuccess: LiveData<String> = _isSuccess

    val memberLimit = MutableLiveData<String>()
    val roomName = MutableLiveData<String>()
    val nickname = MutableLiveData<String>()
    val roomPassword = MutableLiveData<String>()
    val areaType = MutableLiveData(0)
    val areaDetail = MutableLiveData(500)
    val address = MutableLiveData<String>(null)
    val latitude = MutableLiveData<Double>()
    val longitude = MutableLiveData<Double>()

    fun createRoom() {

        val nickname = nickname.value ?: ""
        val roomName = roomName.value ?: ""
        val roomPassword = roomPassword.value
        val memberLimit = memberLimit.value ?: "0"

        val areaType = areaType.value ?: 0

        // 거리별
        val areaDetail = areaDetail.value ?: 0
        val latitude = latitude.value ?: 0.0
        val longitude = longitude.value ?: 0.0

        // 지역별
        val address = address.value ?: ""

        Log.d("TAG", "createRoom: $nickname, $roomName, $roomPassword, $memberLimit, $areaType, $areaDetail, $latitude, $longitude, $address")

        if (
            roomName.isNotEmpty() && memberLimit.toInt() > 1 &&
            ((latitude != 0.0 && longitude != 0.0 && areaDetail != 0) || address.isNotEmpty()) &&
            areaDetail > 0 && nickname.isNotEmpty()
        ) {
            viewModelScope.launch {
                val createRoom =
                    CreateRoom(
                        roomName,
                        nickname,
                        roomPassword,
                        memberLimit.toInt(),
                        latitude,
                        longitude,
                        areaType,
                        areaDetail,
                        address
                    )

                val msgResponse = roomsRepository.postCreateRoom(createRoom)
                msgResponse.raw()
                when {
                    msgResponse.isSuccessful -> {
                        _isSuccess.postValue(msgResponse.message())
                    }
                    msgResponse.code() == 400 -> {
                        _isFailure.postValue("중복된 닉네임이 존재합니다")
                    }
                }
            }
        } else {
            _isFailure.postValue("올바른 값을 입력해주세요")
        }
    }
}