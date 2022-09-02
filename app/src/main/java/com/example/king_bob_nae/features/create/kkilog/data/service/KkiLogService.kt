package com.example.king_bob_nae.features.create.kkilog.data.service

import com.example.king_bob_nae.features.create.kkilog.data.dto.UpLoadResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface KkiLogService {

    @Multipart
    @POST("/logs")
    suspend fun postKkiLog(
        @Part images: List<MultipartBody.Part>,
        @Part title: MultipartBody.Part,
        @Part description: MultipartBody.Part?,
        @Part kick: MultipartBody.Part?
    ): UpLoadResponse
}