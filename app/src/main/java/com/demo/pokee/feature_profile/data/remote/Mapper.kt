package com.demo.pokee.feature_profile.data.remote

import com.demo.pokee.feature_profile.data.remote.dto.UserProfileDto
import com.demo.pokee.feature_profile.domain.model.UserProfile

fun UserProfileDto.toUserProfile():UserProfile{
    return UserProfile(user_name = user_name, id = id, phone_number = phone_number, display_name = display_name)
}