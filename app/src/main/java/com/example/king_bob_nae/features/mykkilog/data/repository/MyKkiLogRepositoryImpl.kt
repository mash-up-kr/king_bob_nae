package com.example.king_bob_nae.features.mykkilog.data.repository

import com.example.king_bob_nae.features.mykkilog.data.MyKkiLogThumbNail
import com.example.king_bob_nae.features.mykkilog.data.service.MyKkiLogService

class MyKkiLogRepositoryImpl(
    private val service: MyKkiLogService
) : MyKkiLogRepository {
    override suspend fun getMyKkiLog(): List<MyKkiLogThumbNail>? {
        return runCatching {
            service.getMyKkiLog()
        }.getOrNull()
    }


    override suspend fun getMyDetailKkiLog(): List<MyKkiLogThumbNail>? {
        return runCatching {
            service.getMyDetailKkiLog()
        }.getOrNull()
    }
}