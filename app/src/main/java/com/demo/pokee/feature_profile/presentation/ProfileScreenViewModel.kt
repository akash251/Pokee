package com.demo.pokee.feature_profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.pokee.feature_profile.domain.repo.ProfileRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val profileRepo: ProfileRepo
): ViewModel() {

    private val _state = MutableStateFlow(ProfileScreenState())
    val state = _state.asStateFlow()

    init {
        getUserProfile()

    }

    private fun getUserProfile(){
        viewModelScope.launch {

            profileRepo.getUserProfileDetails().collectLatest {user ->
                _state.update {
                    it.copy(
                        displayName = user.display_name,
                        userName = user.user_name,
                        imageUri = profileRepo.getImageUri() ?: ""
                    )
                }
                println("the image uri = ${profileRepo.getImageUri()}")

            }
        }
    }
}