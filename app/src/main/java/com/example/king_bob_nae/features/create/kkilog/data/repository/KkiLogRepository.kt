package com.example.king_bob_nae.features.create.kkilog.data.repository

import com.example.king_bob_nae.features.create.kkilog.data.dto.UpLoadDto
import com.example.king_bob_nae.features.create.kkilog.data.dto.UpLoadResponse

interface KkiLogRepository {
    suspend fun postKkiLog(dto: UpLoadDto): UpLoadResponse
}