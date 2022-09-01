package com.example.king_bob_nae.features.create.detail.data.repository.impl

import com.example.king_bob_nae.features.create.detail.data.DetailKkiLogDto
import com.example.king_bob_nae.features.create.detail.data.repository.DetailKkiLogRepository
import com.example.king_bob_nae.features.create.detail.data.service.DetailKkiLogService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class DetailKkiLogRepositoryImpl @Inject constructor(
    private val service: DetailKkiLogService
) : DetailKkiLogRepository {
    override fun requestDetailKkiLog(
        brandImage: MultipartBody.Part,
        recipeImages: List<MultipartBody.Part>,
        recipes: RequestBody,
        title: RequestBody,
        description: RequestBody,
        ingredients: RequestBody
    ): Flow<DetailKkiLogDto> = flow {
        emit(
            service.requestDetailKkiLog(
                brandImage,
                recipeImages,
                recipes,
                title,
                description,
                ingredients
            )
        )
    }
}
