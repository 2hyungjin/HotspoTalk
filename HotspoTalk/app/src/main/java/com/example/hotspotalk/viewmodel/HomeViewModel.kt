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
class HomeViewModel @Inject constructor(
    private val roomsRepository: RoomsRepository
) : ViewModel() {

    // homeViewPager
    val enterableVis = MutableLiveData(false)
    val notEnterableVis = MutableLiveData(false)

    private val _isSuccessCoordinateRooms = MutableLiveData<List<RoomInfo>>(ArrayList())
    val isSuccessCoordinateRooms = _isSuccessCoordinateRooms

    private val _isFailureCoordinateRooms = MutableLiveData<String>()
    val isFailureCoordinateRooms = _isFailureCoordinateRooms

    private val _isSuccessEnteredRooms = MutableLiveData<List<RoomInfo>>(ArrayList())
    val isSuccessEnteredRooms = _isSuccessEnteredRooms

    private val _isFailureEnteredRooms = MutableLiveData<String>()
    val isFailureEnteredRooms = _isFailureEnteredRooms

    fun getRoomsByCoordinate(latitude: Int, longitude: Int) {
        viewModelScope.launch {
            val roomsResponse = roomsRepository.getRoomsByCoordinate(latitude, longitude)
            when {
                roomsResponse.isSuccessful -> {
                    _isSuccessCoordinateRooms.value = roomsResponse.body()
                }
                roomsResponse.code() in 400..499 -> {
                    _isFailureCoordinateRooms.value = roomsResponse.message()
                }
            }
        }
    }

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