package com.example.hotspotalk.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotspotalk.data.entity.response.RoomInfo
import com.example.hotspotalk.data.repository.RoomsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoordinateRoomViewModel @Inject constructor(
    private val roomsRepository: RoomsRepository
) : ViewModel() {

    val roomVis = MutableLiveData(false)

    private val _isSuccessCoordinateRooms = MutableLiveData<List<RoomInfo>>()
    val isSuccessCoordinateRooms = _isSuccessCoordinateRooms

    private val _isFailureCoordinateRooms = MutableLiveData<String>()
    val isFailureCoordinateRooms = _isFailureCoordinateRooms

    fun getRoomsByCoordinate(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            val roomsResponse = roomsRepository.getRoomsByCoordinate(latitude, longitude)
            when {
                roomsResponse.isSuccessful -> {
                    _isSuccessCoordinateRooms.value = roomsResponse.body()

                }
                roomsResponse.code() in 400..500 -> {
                    _isFailureCoordinateRooms.value = roomsResponse.message()
                }
            }
        }
    }
}