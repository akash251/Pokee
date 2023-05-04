package com.demo.pokee.feature_auth.domain.repo

import android.app.Activity
import com.demo.pokee.feature_auth.domain.model.UserDetails
import kotlinx.coroutines.flow.Flow


interface AuthRepository {

    suspend fun verifyPhoneNumber(phoneNumber : String,activity: Activity) : Flow<String?>

    suspend fun getSmsCode() : Flow<String?>

    suspend fun veryOtpAndSignIn(otp :String) : Boolean

    suspend fun postUserDetails(details: UserDetails)
}