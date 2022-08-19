package com.example.king_bob_nae.features.home.data

import com.example.king_bob_nae.features.home.data.freindlist.Friends
import com.example.king_bob_nae.features.home.data.userstate.HomeStatusResult
import retrofit2.http.GET

interface HomeApi {

    @GET("home/status")
    suspend fun getHomeStatus(): HomeStatusResult

    @GET("home/friends")
    suspend fun getFriendsList(): Friends
}
