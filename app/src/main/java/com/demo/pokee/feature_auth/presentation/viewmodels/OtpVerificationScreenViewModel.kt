package com.demo.pokee.feature_auth.presentation.viewmodels

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.pokee.feature_auth.domain.repo.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpVerificationScreenViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    private var _otpCode= MutableStateFlow("")
    val otpCode = _otpCode.asStateFlow()

    private var _isLoading= MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private var timerCounter = 60
    val timer = flow {
        while (timerCounter>=0){
            delay(1_000)
            emit(timerCounter--)
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(10000),
        60
    )





    fun onOtpCodeChange(otp:String){
        _otpCode.value = otp
    }

    fun verifyOtpCode(otp: String, onVerificationSuccess: () ->Unit , onVerificationFailed: () ->Unit){
        viewModelScope.launch {
            _isLoading.value = true
            val isSuccess = repository.veryOtpAndSignIn(otp)
            if (isSuccess){
                _isLoading.value = false
                onVerificationSuccess()
            } else{
                onVerificationFailed()
            }

        }
    }

    fun verifyPhoneNumber(activity: Activity,phoneNumber:String){
        viewModelScope.launch {
            repository.verifyPhoneNumber(phoneNumber,activity).collectLatest {
                it?.let{
                    _otpCode.value =it
                }
            }
        }
    }

    fun getOtpCode(){
        viewModelScope.launch {

            repository.getSmsCode().collectLatest {
                it?.let {
                    _otpCode.value = it
                }
            }
        }
    }




}
