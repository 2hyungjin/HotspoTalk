package com.example.hotspotalk.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotspotalk.data.entity.response.EnteredRoomInfo
import com.example.hotspotalk.data.entity.response.RoomInfo
import com.example.hotspotalk.data.repository.RoomsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnteredRoomViewModel @Inject constructor(
    private val roomsRepository: RoomsRepository
) : ViewModel() {

    val roomVis = MutableLiveData<Boolean>()

    private val _isSuccessEnteredRooms = MutableLiveData<List<EnteredRoomInfo>>(ArrayList())
    val isSuccessEnteredRooms = _isSuccessEnteredRooms

    private val _isFailureEnteredRooms = MutableLiveData<String>()
    val isFailureEnteredRooms = _isFailureEnteredRooms

    fun getEnteredRooms() {
        viewModelScope.launch {
            val roomsResponse = roomsRepository.getEnteredRooms()
            when {
                roomsResponse.isSuccessful -> {
                    _isSuccessEnteredRooms.value = roomsResponse.body()
                }
                roomsResponse.code() in 400..499 -> {
                    _isFailureEnteredRooms.value = roomsResponse.message()
                }
            }
        }
    }
}