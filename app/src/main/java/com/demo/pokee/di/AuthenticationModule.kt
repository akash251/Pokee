package com.demo.pokee.di

import android.content.Context
import android.content.SharedPreferences
import com.demo.pokee.feature_auth.data.prefs.AuthPreferencesImpl
import com.demo.pokee.feature_auth.data.remote.AuthApi
import com.demo.pokee.feature_auth.data.repo.AuthRepositoryImpl
import com.demo.pokee.feature_auth.domain.prefs.AuthPreferences
import com.demo.pokee.feature_auth.domain.repo.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AuthenticationModule {

    @Provides
    @ViewModelScoped
    fun provideAuthPref(sharedPreferences: SharedPreferences) : AuthPreferences{
        return AuthPreferencesImpl(sharedPreferences)
    }

    @Provides
    @ViewModelScoped
    fun provideAuthRepo(auth: FirebaseAuth,preferences: AuthPreferences,api: AuthApi) : AuthRepository{
        return AuthRepositoryImpl(auth,preferences,api)
    }
}