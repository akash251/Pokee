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
class LastNameScreenViewModel @Inject constructor(
    private val authPreferences: AuthPreferences
) : ViewModel() {

    private var _lastName = MutableStateFlow("")
    val lastName = _lastName.asStateFlow()

    fun onLastNameChange(lastName:String){
        _lastName.value = lastName
    }

    fun saveLastName(){
        viewModelScope.launch {
            println("the last name ")
            authPreferences.saveLastName(_lastName.value)
        }
    }

}