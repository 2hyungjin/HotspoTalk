package com.example.hotspotalk.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotspotalk.data.entity.response.RoomInfo
import com.example.hotspotalk.data.repository.AccountRepository
import com.example.hotspotalk.data.repository.RoomsRepository
import com.example.hotspotalk.view.util.Preference.token
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val roomsRepository: RoomsRepository,
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _isFailure = MutableLiveData<String>()
    val isFailure: LiveData<String> = _isFailure
    // homeViewPager
    val enterableVis = MutableLiveData(false)

    fun postToken(devToken: String) {
        viewModelScope.launch {
            val response = accountRepository.postDevToken(devToken)
            when {
                response.code() in 400..499 -> {
                    _isFailure.value = response.message()
                }
            }
        }
    }

}