package com.demo.pokee.feature_profile.data.remote

import com.demo.pokee.feature_profile.data.remote.dto.UserProfileDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileApi {

    @GET("/v1/user/{id}")
    suspend fun getUserProfile(
        @Path("id") id:String
    ) : UserProfileDto

    companion object{
        const val BASE_URL = "http://user-service.pokee.app"
    }
}