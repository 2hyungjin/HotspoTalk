package com.example.hotspotalk.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotspotalk.data.entity.request.Enter
import com.example.hotspotalk.data.repository.RoomsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinChattingViewModel @Inject constructor(
    private val roomsRepository: RoomsRepository
) : ViewModel() {

    val name = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    private val _isSuccess = MutableLiveData<String>()
    val isSuccess = _isSuccess

    private val _isFailure = MutableLiveData<String>()
    val isFailure = _isFailure

    fun putJoinChatting(id: Int) {
        val enter = Enter(id, name.value!!, pw.value!!)

        viewModelScope.launch {
            val res = roomsRepository.postEnterRoom(enter)

            when {
                res.isSuccessful ->
                    _isSuccess.value = res.message()

                res.code() in 400..499 ->
                    _isFailure.value = res.message()
            }
        }
    }
}