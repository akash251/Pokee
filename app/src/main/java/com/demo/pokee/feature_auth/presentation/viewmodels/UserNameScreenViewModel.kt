package com.demo.pokee.feature_auth.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.pokee.feature_auth.domain.model.UserDetails
import com.demo.pokee.feature_auth.domain.prefs.AuthPreferences
import com.demo.pokee.feature_auth.domain.repo.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserNameScreenViewModel @Inject constructor(
    private val authPreferences: AuthPreferences,
    private val repository: AuthRepository
) : ViewModel() {

    private var _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    fun onUserNameChange(phone:String){
        _userName.value = phone
    }

    fun saveUserName(){
        viewModelScope.launch {
            authPreferences.saveUserName(_userName.value)
            repository.postUserDetails(
              UserDetails(
                    id = authPreferences.getUid()!!,
                    first_name = authPreferences.getFirstName()!!,
                    last_name = authPreferences.getLastName()!!,
                    phone_number = authPreferences.getPhoneNum()!!,
                    user_name = _userName.value
                )
            )
            println("the uid = ${authPreferences.getUid()!!}")
        }
    }

}