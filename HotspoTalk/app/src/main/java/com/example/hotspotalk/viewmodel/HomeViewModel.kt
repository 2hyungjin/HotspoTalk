package com.example.hotspotalk.viewmodel

import android.util.Log
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

    // home
    val isEnterableChecked = MutableLiveData(false)

    // homeViewPager
    val enterableVis = MutableLiveData(false)
    val notEnterableVis = MutableLiveData(false)

    private val _isSuccessEnterable = MutableLiveData<List<RoomInfo>>(ArrayList())
    val isSuccessEnterable = _isSuccessEnterable

    private val _isFailureEnterable = MutableLiveData<String>()
    val isFailureEnterable = _isFailureEnterable

    private val _isSuccessNotEnterable = MutableLiveData<List<RoomInfo>>(ArrayList())
    val isSuccessNotEnterable = _isSuccessNotEnterable

    private val _isFailureNotEnterable = MutableLiveData<String>()
    val isFailureNotEnterable = _isFailureNotEnterable

    fun getRoomsEnterable(latitude: Int, longitude: Int) {
        viewModelScope.launch {
            try {
                val res = roomsRepository.getRooms(latitude, longitude)

                if (res.isSuccessful) {
                    _isSuccessEnterable.postValue(res.body())
                } else {
                    Log.d("", "")
                }
            } catch (e: Exception) {
                _isFailureEnterable.postValue(e.message)
            }
        }
    }

    fun getRoomsNotEnterable() {
        viewModelScope.launch {
            try {
//                _isSuccessNotEnterable.postValue(list)
            } catch (e: Exception) {
                _isFailureNotEnterable.postValue(e.message)
            }
        }
    }
}