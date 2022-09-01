package com.example.king_bob_nae.features.create.kkilog.data.service

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface KkiLogService {

    @Multipart
    @POST("/log")
    suspend fun postKkiLog(
        @Part images: List<MultipartBody.Part>,
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody,
        @Part("kick") kick: RequestBody
    )
}