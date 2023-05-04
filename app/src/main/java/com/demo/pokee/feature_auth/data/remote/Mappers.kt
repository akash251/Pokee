package com.demo.pokee.feature_auth.data.remote

import com.demo.pokee.feature_auth.data.remote.dto.UserDetailsDto
import com.demo.pokee.feature_auth.domain.model.UserDetails

fun UserDetails.toUserDetailsDto() : UserDetailsDto{
    return UserDetailsDto(
        id, first_name, last_name, user_name, phone_number
    )
}