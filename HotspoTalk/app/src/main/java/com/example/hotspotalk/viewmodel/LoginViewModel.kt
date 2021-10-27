package com.example.hotspotalk.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotspotalk.data.entity.repuest.Login
import com.example.hotspotalk.data.entity.response.Token
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    val id = MutableLiveData("")
    val pw = MutableLiveData("")

    private val _isSuccess = MutableLiveData<Token>()
    val isSuccess = _isSuccess

    private val _isFailure = MutableLiveData<String>()
    val isFailure = _isFailure

    // todo
    private val devToken = ""

    fun login() {
        viewModelScope.launch {
            val login = Login(id.value!!, pw.value!!, devToken)

            try {

            } catch (e: Exception) {
                _isFailure.postValue(e.message)
            }
        }
    }
}