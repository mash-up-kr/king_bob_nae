package com.example.king_bob_nae.features.myprofile.data

import com.example.king_bob_nae.features.myprofile.data.userfollow.follow.UserFriendFollowResult
import com.example.king_bob_nae.features.myprofile.data.userfollow.getuserfollow.UserFollowResult
import com.example.king_bob_nae.features.myprofile.data.userfollow.unfollow.UserFriendUnFollowResult
import retrofit2.http.*

interface ProfileApi {

    @GET("/user")
    suspend fun getUserProfile(): UserProfileResult

    @GET("/user/friends")
    suspend fun getUserFollowList(
        @Query("type") type: String,
        @Query("keyword") keyword: String?,
    ): UserFollowResult

    @FormUrlEncoded
    @POST("/user/friend/unfollow")
    suspend fun postUserFriendUnFollow(
        @Field("id") id: Int,
    ): UserFriendUnFollowResult

    @FormUrlEncoded
    @POST("/user/friend/follow")
    suspend fun postUserFriendFollow(
        @Field("id") id: Int,
    ): UserFriendFollowResult
}
