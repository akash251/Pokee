package com.demo.pokee.feature_profile.domain.repo

import android.net.Uri
import com.demo.pokee.feature_profile.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface ProfileRepo {

    fun getUserProfileDetails() : Flow<UserProfile>

    suspend fun getImageUri() : String?
}