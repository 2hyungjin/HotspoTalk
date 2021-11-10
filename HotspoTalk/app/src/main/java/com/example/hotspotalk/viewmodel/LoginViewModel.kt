package com.example.hotspotalk.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotspotalk.data.entity.request.Login
import com.example.hotspotalk.data.entity.response.Token
import com.example.hotspotalk.data.repository.AccountRepository
import com.example.hotspotalk.view.util.Event
import com.example.hotspotalk.view.util.Preference.token
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountRepository: AccountRepository
): ViewModel() {

    val id = ObservableField<String>()
    val pw = ObservableField<String>()

    private val _isSuccess = MutableLiveData<Event<Any?>>()
    val isSuccess = _isSuccess

    private val _isFailure = MutableLiveData<Event<String>>()
    val isFailure = _isFailure

    fun login() {
        viewModelScope.launch {
            val login = Login(id.get() ?: "", pw.get() ?: "")
            val loginResponse = accountRepository.postLogin(login)
            when {
                loginResponse.isSuccessful ->
                    _isSuccess.value = Event(loginResponse.body())

                else ->
                    _isFailure.value = Event(loginResponse.message())
            }
        }
    }

    fun autoLogin() {
        viewModelScope.launch {
            val loginResponse = accountRepository.postAutoLogin()
            when {
                loginResponse.isSuccessful ->
                    _isSuccess.value = Event(loginResponse.body())
            }
        }
    }
}