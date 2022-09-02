package com.example.king_bob_nae.features.create.detail.data.repository

import com.example.king_bob_nae.features.create.detail.data.DetailKkiLogDto
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface DetailKkiLogRepository {
    fun requestDetailKkiLog(
        brandImage: MultipartBody.Part,
        recipeImages: List<MultipartBody.Part>,
        recipes: RequestBody,
        title: RequestBody,
        description: RequestBody,
        ingredients: RequestBody
    ): Flow<DetailKkiLogDto>
}
