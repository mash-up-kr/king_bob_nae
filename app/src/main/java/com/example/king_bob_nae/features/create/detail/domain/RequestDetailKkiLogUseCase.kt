package com.example.king_bob_nae.features.create.detail.domain

import com.example.king_bob_nae.base.di.DispatcherModule
import com.example.king_bob_nae.features.create.detail.data.repository.DetailKkiLogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import javax.inject.Inject

class RequestDetailKkiLogUseCase @Inject constructor(
    private val detailKkiLogRepository: DetailKkiLogRepository,
    @DispatcherModule.DispatcherDefault private val defaultDisPatcher: CoroutineDispatcher
) {
    operator fun invoke(
        brandImage: MultipartBody.Part,
        recipeImages: List<MultipartBody.Part>,
        recipes: String,
        title: String,
        description: String,
        ingredients: String
    ) =
        detailKkiLogRepository.requestDetailKkiLog(
            brandImage,
            recipeImages,
            recipes,
            title,
            description,
            ingredients
        ).flowOn(defaultDisPatcher)
}
