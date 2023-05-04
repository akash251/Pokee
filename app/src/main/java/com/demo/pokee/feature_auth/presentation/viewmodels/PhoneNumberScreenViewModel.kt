package com.demo.pokee.feature_auth.presentation.viewmodels

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.pokee.feature_auth.domain.repo.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhoneNumberScreenViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    private var _phoneNumber = MutableStateFlow("+91")
    val phoneNumber = _phoneNumber.asStateFlow()

    fun onPhoneNumberChange(phone:String){
        _phoneNumber.value = phone
    }



}