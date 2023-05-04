package com.demo.pokee.feature_auth.data.repo

import android.app.Activity
import com.demo.pokee.feature_auth.data.remote.AuthApi
import com.demo.pokee.feature_auth.data.remote.toUserDetailsDto
import com.demo.pokee.feature_auth.domain.model.UserDetails
import com.demo.pokee.feature_auth.domain.prefs.AuthPreferences
import com.demo.pokee.feature_auth.domain.repo.AuthRepository
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val preferences: AuthPreferences,
    private val authApi: AuthApi
): AuthRepository {

    private lateinit var phoneAuthCredential: PhoneAuthCredential

    private lateinit var verificationID : String
    private lateinit var resendToken : PhoneAuthProvider.ForceResendingToken


    override suspend fun verifyPhoneNumber(phoneNumber: String,activity: Activity) : Flow<String?>{
        return callbackFlow {
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(120L, TimeUnit.SECONDS)
                .setActivity(activity)
                .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                        phoneAuthCredential = credential
                        CoroutineScope(Dispatchers.Main).launch {
                            send(credential.smsCode)
                            signInWithPhoneAuthCredential(credential)
                        }
                    }

                    override fun onVerificationFailed(e: FirebaseException) {

                        when (e) {
                            is FirebaseAuthInvalidCredentialsException -> {
                            }

                            is FirebaseTooManyRequestsException -> {

                            }

                            is FirebaseAuthMissingActivityForRecaptchaException -> {

                            }
                        }
                    }

                    override fun onCodeSent(
                        verificationId: String,
                        token: PhoneAuthProvider.ForceResendingToken,
                    ) {
                        verificationID = verificationId
                        resendToken = token
                    }
                })
                .build()

            PhoneAuthProvider.verifyPhoneNumber(options)
            awaitClose{cancel()}
        }
    }

    override suspend fun postUserDetails(details: UserDetails) {
        try {
            authApi.postUserDetails(details.toUserDetailsDto())
        } catch (e:Exception){
            e.printStackTrace()
        }
    }

    override suspend fun veryOtpAndSignIn(otp :String) : Boolean{
        return try {
            if (::verificationID.isInitialized) {
                val credential = PhoneAuthProvider.getCredential(verificationID, otp)
                val user = signInWithPhoneAuthCredential(credential)
                user?.let {
                    preferences.saveUid(it.uid)
                    it.phoneNumber?.let {number ->
                        preferences.savePhoneNum(number)
                    }
                }
                user != null
            } else {
                false
            }

        } catch (e:Exception){
            false
        }
    }

    override suspend fun getSmsCode() : Flow<String?> {
        return callbackFlow {

            if (::phoneAuthCredential.isInitialized) {
                send(phoneAuthCredential.smsCode)
            } else {

                send(null)
            }
            awaitClose { cancel() }
        }

    }

    private suspend fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential): FirebaseUser? {

         return auth.signInWithCredential(credential).await().user

    }
}