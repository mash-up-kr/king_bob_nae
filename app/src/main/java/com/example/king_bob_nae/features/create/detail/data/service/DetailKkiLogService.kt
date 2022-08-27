package com.example.king_bob_nae.features.create.detail.data.service

import com.example.king_bob_nae.features.create.detail.data.DetailKkiLogDto
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface DetailKkiLogService {

    @Multipart
    @POST("/detail-log")
    suspend fun requestDetailKkiLog(
        @Part brandImage: MultipartBody.Part, // 끼록 메인 음식 이미지
        @Part recipeImages: List<MultipartBody.Part>, // 레시피 이미지 리스트
        @Part recipes: String, // 레시피 내용 리스트
        @Part title: String, // 레시피 제목
        @Part description: String, // 레시피 한줄 설명
        @Part ingredients: String // 레시피 재료 리스트
    ): DetailKkiLogDto
}
