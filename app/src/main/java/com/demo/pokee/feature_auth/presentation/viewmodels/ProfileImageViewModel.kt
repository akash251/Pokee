package com.demo.pokee.feature_auth.presentation.viewmodels

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.pokee.feature_auth.domain.prefs.AuthPreferences
import com.demo.pokee.util.handleUri
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileImageViewModel @Inject constructor(
    private val preferences: AuthPreferences
): ViewModel() {

    private val _profileImageUriString = MutableStateFlow("")
    val profileImageUriString = _profileImageUriString.asStateFlow()

    fun onProfileChange(uri:Uri,context: Context){
        _profileImageUriString.value = handleUri(context, uri) ?: ""
    }

    fun saveProfileImage(){
        viewModelScope.launch {
            preferences.saveProfileUri(_profileImageUriString.value)
        }
    }

}