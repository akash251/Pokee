package com.demo.pokee.feature_auth.data.remote.dto


data class UserDetailsDto(
    val id : String,
    val first_name : String,
    val last_name : String,
    val user_name  : String,
    val phone_number : String
)