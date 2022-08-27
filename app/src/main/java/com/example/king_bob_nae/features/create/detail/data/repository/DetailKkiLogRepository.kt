package com.example.king_bob_nae.features.create.detail.data.repository

import com.example.king_bob_nae.features.create.detail.data.DetailKkiLogDto
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface DetailKkiLogRepository {
    fun requestDetailKkiLog(
        brandImage: MultipartBody.Part,
        recipeImages: List<MultipartBody.Part>,
        recipes: String,
        title: String,
        description: String,
        ingredients: String
    ): Flow<DetailKkiLogDto>
}
