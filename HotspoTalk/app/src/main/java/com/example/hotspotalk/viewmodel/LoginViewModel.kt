package com.example.hotspotalk.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotspotalk.data.entity.repuest.Login
import com.example.hotspotalk.data.entity.response.Token
import com.example.hotspotalk.data.repository.AccountRepository
import com.example.hotspotalk.data.repository.RoomsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountRepository: AccountRepository
): ViewModel() {

    val id = MutableLiveData("")
    val pw = MutableLiveData("")

    private val _isSuccess = MutableLiveData<Token>()
    val isSuccess = _isSuccess

    private val _isFailure = MutableLiveData<String>()
    val isFailure = _isFailure

    val devToken = ""

    fun login() {
        viewModelScope.launch {
            val login = Login(id.value!!, pw.value!!, devToken)

            val loginResponse = accountRepository.postLogin(login)
            when {
                loginResponse.isSuccessful ->
                    _isSuccess.value = loginResponse.body()

                loginResponse.code() in 400..499 ->
                    _isFailure.value = loginResponse.message()
            }
        }
    }
}