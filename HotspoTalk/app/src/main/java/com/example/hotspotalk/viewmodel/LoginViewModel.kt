package com.example.hotspotalk.viewmodel

import androidx.databinding.ObservableField
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

    val id = ObservableField<String>()
    val pw = ObservableField<String>()

    private val _isSuccess = MutableLiveData<Token>()
    val isSuccess = _isSuccess

    private val _isFailure = MutableLiveData<String>()
    val isFailure = _isFailure

    var devToken = ""

    fun login() {
        viewModelScope.launch {
            val login = Login(id.get()?:"", pw.get()?:"", devToken)

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