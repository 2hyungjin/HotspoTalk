package com.example.hotspotalk.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotspotalk.data.entity.request.SignUp
import com.example.hotspotalk.data.entity.response.Token
import com.example.hotspotalk.data.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    val id = ObservableField<String>()
    val pw = ObservableField<String>()

    private val _isSuccess = MutableLiveData<Token>()
    val isSuccess = _isSuccess

    private val _isFailure = MutableLiveData<String>()
    val isFailure = _isFailure

    fun signUp() {
        viewModelScope.launch {
            try {
                val signUp = SignUp(
                    id.get() ?: "",
                    pw.get() ?: ""
                )
                val response = accountRepository.postSignUp(signUp)

                when {
                    response.isSuccessful -> {
                        _isSuccess.postValue(response.body())
                    }
                    response.code() in 400..500 -> {
                        _isFailure.postValue(response.message())
                    }
                }
            } catch (e: Exception) {
                isFailure.postValue(e.message)
            }
        }
    }
}