package com.demo.pokee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.pokee.feature_auth.domain.prefs.AuthPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authPreferences: AuthPreferences
): ViewModel() {

    private val _mainState = MutableStateFlow(MainState())
    val mainState = _mainState.asStateFlow()

    init {
        getMainState()
    }

    fun getMainState() {
        viewModelScope.launch {
            println("the last name = ${authPreferences.getFirstName()}")
            println("the last name = ${authPreferences.getLastName()}")
            println("the last name = ${authPreferences.getUid()}")
            println("the last name = ${authPreferences.getImageUriString()}")
            _mainState.update {
                it.copy(
                    isUserLoggedIn = authPreferences.getUid() != null,
                    isFirstNameProvided = authPreferences.getFirstName() != null,
                    isImageProvided = authPreferences.getImageUriString() != null,
                    isLastNameProvided = authPreferences.getLastName() != null,
                    isUserNameProvided = authPreferences.getUserName() != null
                )
            }
        }
    }

}

data class MainState(
    val isUserLoggedIn : Boolean = false,
    val isFirstNameProvided : Boolean = false,
    val isLastNameProvided : Boolean = false,
    val isUserNameProvided : Boolean = false,
    val isImageProvided : Boolean = false
)