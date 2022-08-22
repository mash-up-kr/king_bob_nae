package com.example.king_bob_nae.features.myprofile.data

import retrofit2.http.GET

interface ProfileApi {

    @GET("/user")
    suspend fun getUserProfile(): UserProfileResult
}
