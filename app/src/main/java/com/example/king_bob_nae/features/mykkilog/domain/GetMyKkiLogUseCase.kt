package com.example.king_bob_nae.features.mykkilog.domain

import com.example.king_bob_nae.features.mykkilog.data.MyKkiLogThumbNail
import com.example.king_bob_nae.features.mykkilog.data.repository.MyKkiLogRepository
import javax.inject.Inject

class GetMyKkiLogUseCase @Inject constructor(
    private val repository: MyKkiLogRepository
) {
    suspend operator fun invoke(): List<MyKkiLogThumbNail>? = repository.getMyKkiLog()
}