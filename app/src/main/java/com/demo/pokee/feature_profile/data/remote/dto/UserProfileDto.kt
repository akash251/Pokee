package com.demo.pokee.feature_profile.data.remote.dto

import androidx.annotation.Keep

@Keep
data class UserProfileDto(
    val id : String,
    val display_name : String,
    val user_name  : String,
    val phone_number : String
)
