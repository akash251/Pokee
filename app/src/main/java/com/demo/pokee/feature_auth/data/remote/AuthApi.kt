package com.demo.pokee.feature_auth.data.remote

import com.demo.pokee.feature_auth.data.remote.dto.UserDetailsDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/v1/user/")
    suspend fun postUserDetails(
        @Body data : UserDetailsDto
    ) : UserDetailsDto

    companion object{
        const val BASE_URL = "http://user-service.pokee.app"
    }
}