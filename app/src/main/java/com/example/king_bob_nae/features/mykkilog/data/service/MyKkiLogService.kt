package com.example.king_bob_nae.features.mykkilog.data.service

import com.example.king_bob_nae.features.mykkilog.data.MyKkiLogResponse
import retrofit2.http.GET

interface MyKkiLogService {

    @GET("logs")
    suspend fun getMyKkiLog(): MyKkiLogResponse

    @GET("detail-log")
    suspend fun getMyDetailKkiLog(): MyKkiLogResponse

}