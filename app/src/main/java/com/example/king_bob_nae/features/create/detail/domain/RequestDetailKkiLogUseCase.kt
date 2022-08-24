package com.example.king_bob_nae.features.create.detail.domain

import com.example.king_bob_nae.base.di.DispatcherModule
import com.example.king_bob_nae.features.create.detail.data.repository.DetailKkiLogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RequestDetailKkiLogUseCase @Inject constructor(
    private val detailKkiLogRepository: DetailKkiLogRepository,
    @DispatcherModule.DispatcherDefault private val defaultDisPatcher: CoroutineDispatcher
) {
    suspend operator fun invoke() = withContext(defaultDisPatcher) {
        detailKkiLogRepository.requestDetailKkiLog()
    }
}
