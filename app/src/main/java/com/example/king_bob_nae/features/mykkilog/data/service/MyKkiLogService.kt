package com.example.king_bob_nae.features.mykkilog.data.service

import com.example.king_bob_nae.features.mykkilog.data.MyKkiLogThumbNail
import retrofit2.http.GET

interface MyKkiLogService {

    @GET("logs")
    suspend fun getMyKkiLog(): List<MyKkiLogThumbNail>

    @GET("detail-log")
    suspend fun getMyDetailKkiLog(): List<MyKkiLogThumbNail>

}