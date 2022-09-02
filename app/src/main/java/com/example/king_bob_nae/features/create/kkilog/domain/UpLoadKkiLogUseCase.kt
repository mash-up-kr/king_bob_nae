package com.example.king_bob_nae.features.create.kkilog.domain

import com.example.king_bob_nae.features.create.kkilog.data.dto.UpLoadDto
import com.example.king_bob_nae.features.create.kkilog.data.repository.KkiLogRepository
import javax.inject.Inject

class UpLoadKkiLogUseCase @Inject constructor(
    private val repository: KkiLogRepository
) {
    suspend operator fun invoke(dto: UpLoadDto) = repository.postKkiLog(dto)
}