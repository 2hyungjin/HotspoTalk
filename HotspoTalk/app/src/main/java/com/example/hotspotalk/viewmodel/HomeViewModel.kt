package com.example.hotspotalk.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.response.Msg
import com.example.domain.entity.response.RoomInfo
import com.example.domain.usecase.rooms.GetRoomsEnterableUseCase
import com.example.domain.usecase.rooms.GetRoomsNotEnterableUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRoomsEnterableUseCase: GetRoomsEnterableUseCase,
    private val getRoomsNotEnterableUseCase: GetRoomsNotEnterableUseCase
) : ViewModel() {

    // home
    val isEnterableChecked = MutableLiveData(false)

    // homeViewPager
    val enterableVis = MutableLiveData(false)
    val notEnterableVis = MutableLiveData(false)

    private val _isSuccessEnterable = MutableLiveData<List<RoomInfo>>()
    val isSuccessEnterable = _isSuccessEnterable

    private val _isFailureEnterable = MutableLiveData<String>()
    val isFailureEnterable = _isFailureEnterable

    private val _isSuccessNotEnterable = MutableLiveData<List<RoomInfo>>()
    val isSuccessNotEnterable = _isSuccessNotEnterable

    private val _isFailureNotEnterable = MutableLiveData<String>()
    val isFailureNotEnterable = _isFailureNotEnterable

    fun getRoomsEnterable() {
        val latitude = 1
        val longitude = 1

        viewModelScope.launch {
            try {
                getRoomsEnterableUseCase.buildUseCase(GetRoomsEnterableUseCase.Params(latitude, longitude))
            } catch (e: Exception) {
                _isFailureEnterable.postValue(e.message)
            }
        }
    }

    fun getRoomsNotEnterable() {
        viewModelScope.launch {
            try {
                getRoomsNotEnterableUseCase.buildUseCase()
            } catch (e: Exception) {
                _isFailureNotEnterable.postValue(e.message)
            }
        }
    }
}