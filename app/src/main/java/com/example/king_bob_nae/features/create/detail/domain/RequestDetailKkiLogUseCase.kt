package com.example.king_bob_nae.features.create.detail.domain

import com.example.king_bob_nae.base.di.DispatcherModule
import com.example.king_bob_nae.features.create.detail.data.repository.DetailKkiLogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class RequestDetailKkiLogUseCase @Inject constructor(
    private val detailKkiLogRepository: DetailKkiLogRepository,
    @DispatcherModule.DispatcherDefault private val defaultDisPatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(
        brandImage: MultipartBody.Part,
        recipeImages: List<MultipartBody.Part>,
        recipes: RequestBody,
        title: RequestBody,
        description: RequestBody,
        ingredients: RequestBody
    ) = withContext(defaultDisPatcher) {
        detailKkiLogRepository.requestDetailKkiLog(
            brandImage,
            recipeImages,
            recipes,
            title,
            description,
            ingredients
        ).map {
            it.data.toDetailKkiLogResult()
        }
    }
}
