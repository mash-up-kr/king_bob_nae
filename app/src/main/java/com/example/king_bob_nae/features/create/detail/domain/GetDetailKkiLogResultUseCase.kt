package com.example.king_bob_nae.features.create.detail.domain

import com.example.king_bob_nae.base.di.DispatcherModule
import com.example.king_bob_nae.features.create.detail.data.repository.DetailKkiLogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetDetailKkiLogResultUseCase @Inject constructor(
    private val detailKkiLogRepository: DetailKkiLogRepository,
    @DispatcherModule.DispatcherDefault private val defaultDisPatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(
        userId: Int
    ) = withContext(defaultDisPatcher) {
        detailKkiLogRepository.fetchDetailKkiLogResult(
            userId
        ).map {
            it.data.toDetailKkiLogResult()
        }
    }
}
