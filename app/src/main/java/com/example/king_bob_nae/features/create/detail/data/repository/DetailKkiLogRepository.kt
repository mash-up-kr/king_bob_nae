package com.example.king_bob_nae.features.create.detail.data.repository

import com.example.king_bob_nae.features.create.detail.data.DetailKkiLogDto
import kotlinx.coroutines.flow.Flow

interface DetailKkiLogRepository {
    fun requestDetailKkiLog(): Flow<DetailKkiLogDto>
}
