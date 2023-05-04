package com.demo.pokee.feature_auth.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.pokee.feature_auth.domain.prefs.AuthPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstNameScreenViewModel @Inject constructor(
    private val authPreferences: AuthPreferences
) : ViewModel() {

    private var _firstName = MutableStateFlow("")
    val firstName = _firstName.asStateFlow()

    fun onFirstNameChange(name:String){
        _firstName.value = name
    }

    fun saveFirstName(){
        viewModelScope.launch {
            authPreferences.saveFirstName(_firstName.value)
        }
    }

}