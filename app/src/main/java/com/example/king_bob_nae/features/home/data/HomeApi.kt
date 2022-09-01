package com.example.king_bob_nae.features.home.data

import com.example.king_bob_nae.features.home.data.freindlist.Friends
import com.example.king_bob_nae.features.home.data.friendshome.AllKkilogResult
import com.example.king_bob_nae.features.home.data.levelup.LevelUpResult
import com.example.king_bob_nae.features.home.data.userstate.HomeStatusResult
import com.example.king_bob_nae.features.myprofile.data.UserProfile
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HomeApi {

    @GET("home/status")
    suspend fun getHomeStatus(): HomeStatusResult

    @GET("home/friends")
    suspend fun getFriendsList(): Friends

    @GET("user") // 유저 정보 조회
    suspend fun getUserInfo(): UserProfile

    @GET("home/status/{userId}") // 친구 상태 정보 조회
    suspend fun getFriendsStatus(
        @Path("userId")
        userId: Int,
    ): HomeStatusResult

    @POST("home/level-up")
    suspend fun postLevelUp(): LevelUpResult

    @GET("user/logs/{userId}")
    suspend fun getUserAllKkilog(
        @Path("userId")
        userId: Int,
    ): AllKkilogResult
}
