package com.example.king_bob_nae.features.myprofile.data

import com.example.king_bob_nae.features.myprofile.data.userfollow.getuserfollow.UserFollowResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileApi {

    @GET("/user")
    suspend fun getUserProfile(): UserProfileResult

    @GET("/user/friends")
    suspend fun getUserFollowList(
        @Query("type") type: String,
        @Query("keyword") keyword: String?,
    ): UserFollowResult

}
