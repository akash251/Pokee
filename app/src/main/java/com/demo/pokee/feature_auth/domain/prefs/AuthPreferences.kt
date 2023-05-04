package com.demo.pokee.feature_auth.domain.prefs

interface AuthPreferences {

    suspend fun saveUid(uid:String)
    suspend fun getUid() : String?

    suspend fun savePhoneNum(phone:String)
    suspend fun getPhoneNum() : String?

    suspend fun saveFirstName(firstName:String)
    suspend fun getFirstName() : String?

    suspend fun saveLastName(lastName:String)
    suspend fun getLastName() : String?

    suspend fun saveProfileUri(imageUri : String)
    suspend fun getImageUriString() : String?

    suspend fun saveUserName(userName:String)
    suspend fun getUserName() : String?

    companion object{
        const val UID = "uid"
        const val PHONE_NUMBER = "phone_number"
        const val FIRSTNAME = "first_name"
        const val LASTNAME = "last_name"
        const val PROFILE_URI = "profile_uri"
        const val USERNAME = "user_name"
    }
}