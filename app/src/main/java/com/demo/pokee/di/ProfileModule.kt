package com.demo.pokee.di

import com.demo.pokee.feature_auth.domain.prefs.AuthPreferences
import com.demo.pokee.feature_profile.data.remote.ProfileApi
import com.demo.pokee.feature_profile.data.repo.ProfileRepoImpl
import com.demo.pokee.feature_profile.domain.repo.ProfileRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ProfileModule {

    @Provides
    @ViewModelScoped
    fun provideProfileRepo(profileApi: ProfileApi,preferences: AuthPreferences):ProfileRepo{
        return ProfileRepoImpl(profileApi, preferences)
    }
}