package com.example.hotspotalk.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotspotalk.data.entity.repuest.Login
import com.example.hotspotalk.data.entity.response.Token
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    val id = MutableLiveData("")
    val pw = MutableLiveData("")
    val pwCheck = MutableLiveData("")

    val idErr = MutableLiveData<String>()
    val pwErr = MutableLiveData<String>()

    private val _isSuccess = MutableLiveData<Token>()
    val isSuccess = _isSuccess

    private val _isFailure = MutableLiveData<String>()
    val isFailure = _isFailure


    var devToken: String? = null

    fun signUp() {
        viewModelScope.launch {
            if (pw.value != pwCheck.value) {
                return@launch
            }

            try {
                val signUp = Login(
                    id.value!!,
                    pw.value!!,
                    devToken ?: throw java.lang.Exception("디바이스 토큰이 생성되지 않음")
                )
//                _isSuccess.postValue(postSignUpUseCase.buildUseCase(PostSignUpUseCase.Params(signUp)))
            } catch (e: Exception) {
                isFailure.postValue(e.message)
            }
        }
    }
}