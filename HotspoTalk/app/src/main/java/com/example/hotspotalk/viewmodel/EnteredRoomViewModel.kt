package com.example.hotspotalk.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotspotalk.data.entity.response.EnteredRoomInfo
import com.example.hotspotalk.data.entity.response.RoomInfo
import com.example.hotspotalk.data.repository.RoomsRepository
import com.example.hotspotalk.view.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnteredRoomViewModel @Inject constructor(
    private val roomsRepository: RoomsRepository
) : ViewModel() {

    val roomVis = MutableLiveData<Boolean>()
    val enteredRoom = MutableLiveData<List<EnteredRoomInfo>>()

    private val _isSuccessEnteredRooms = MutableLiveData<Event<List<EnteredRoomInfo>>>()
    val isSuccessEnteredRooms = _isSuccessEnteredRooms

    private val _isFailureEnteredRooms = MutableLiveData<Event<String>>()
    val isFailureEnteredRooms = _isFailureEnteredRooms

    fun getEnteredRooms() {
        viewModelScope.launch {
            val roomsResponse = roomsRepository.getEnteredRooms()
            when {
                roomsResponse.isSuccessful -> {
                    enteredRoom.postValue(roomsResponse.body())
                    _isSuccessEnteredRooms.value = Event(roomsResponse.body()!!)
                }
                roomsResponse.code() in 400..499 -> {
                    _isFailureEnteredRooms.value = Event(roomsResponse.message())
                }
            }
        }
    }
}