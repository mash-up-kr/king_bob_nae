package com.example.king_bob_nae.features.create.detail.data.service

import com.example.king_bob_nae.features.create.detail.data.DetailKkiLogDto
import retrofit2.http.POST

interface DetailKkiLogService {

    @POST("/detail-log")
    suspend fun requestDetailKkiLog(): DetailKkiLogDto
}
