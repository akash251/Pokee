package com.demo.pokee.feature_auth.data.prefs

import android.content.SharedPreferences
import com.demo.pokee.feature_auth.domain.prefs.AuthPreferences
import javax.inject.Inject

class AuthPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
): AuthPreferences {

    override suspend fun saveUid(uid: String) {
        sharedPreferences.edit()
            .putString(AuthPreferences.UID,uid)
            .apply()
    }

    override suspend fun getUid() : String? {
        return sharedPreferences.getString(AuthPreferences.UID,null)
    }

    override suspend fun savePhoneNum(phone: String) {
        sharedPreferences.edit()
            .putString(AuthPreferences.PHONE_NUMBER,phone)
            .apply()
    }

    override suspend fun getPhoneNum(): String? {
        return sharedPreferences.getString(AuthPreferences.PHONE_NUMBER,null)
    }

    override suspend fun saveFirstName(firstName: String) {
        sharedPreferences.edit()
            .putString(AuthPreferences.FIRSTNAME,firstName)
            .apply()
    }

    override suspend fun getFirstName(): String? {
        return sharedPreferences.getString(AuthPreferences.FIRSTNAME,null)
    }

    override suspend fun saveLastName(lastName: String) {
        sharedPreferences.edit()
            .putString(AuthPreferences.LASTNAME,lastName)
            .apply()
    }

    override suspend fun getLastName(): String? {
        return sharedPreferences.getString(AuthPreferences.LASTNAME,null)

    }

    override suspend fun saveProfileUri(imageUri: String) {
        sharedPreferences.edit()
            .putString(AuthPreferences.PROFILE_URI,imageUri)
            .apply()
    }

    override suspend fun getImageUriString(): String? {
        return sharedPreferences.getString(AuthPreferences.PROFILE_URI,null)
    }

    override suspend fun saveUserName(userName: String) {
        sharedPreferences.edit()
            .putString(AuthPreferences.USERNAME,userName)
            .apply()
    }

    override suspend fun getUserName(): String? {
        return sharedPreferences.getString(AuthPreferences.USERNAME,null)
    }
}