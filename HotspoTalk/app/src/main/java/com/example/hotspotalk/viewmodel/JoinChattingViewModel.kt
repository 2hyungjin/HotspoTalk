package com.example.hotspotalk.viewmodel

import androidx.databinding.ObservableField
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

    val name = ObservableField<String>()
    val pw = ObservableField<String>()

    private val _isSuccess = MutableLiveData<String>()
    val isSuccess = _isSuccess

    private val _isFailure = MutableLiveData<String>()
    val isFailure = _isFailure

    fun putJoinChatting(id: Int) {
        val enter = Enter(name.get() ?: "", pw.get())

        viewModelScope.launch {
            val res = roomsRepository.postEnterRoom(id, enter)

            when {
                res.isSuccessful ->
                    _isSuccess.value = res.message()

                else ->
                    _isFailure.value = res.message()
            }
        }
    }
}