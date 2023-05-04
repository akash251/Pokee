package com.demo.pokee.feature_profile.data.repo

import android.net.Uri
import com.demo.pokee.feature_auth.domain.prefs.AuthPreferences
import com.demo.pokee.feature_profile.data.remote.ProfileApi
import com.demo.pokee.feature_profile.data.remote.toUserProfile
import com.demo.pokee.feature_profile.domain.model.UserProfile
import com.demo.pokee.feature_profile.domain.repo.ProfileRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepoImpl @Inject constructor(
    private val profileApi: ProfileApi,
    private val preferences: AuthPreferences
) : ProfileRepo {

    override fun getUserProfileDetails(): Flow<UserProfile> {
        return flow {
            val id = preferences.getUid() ?: return@flow
            try {
                val dto = profileApi.getUserProfile(id)
                emit(dto.toUserProfile())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override suspend fun getImageUri(): String? {
        return preferences.getImageUriString()
    }
}