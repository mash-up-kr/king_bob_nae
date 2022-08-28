package com.example.king_bob_nae.features.mykkilog.data.repository

import com.example.king_bob_nae.features.mykkilog.data.MyKkiLogThumbNail
import com.example.king_bob_nae.features.mykkilog.data.service.MyKkiLogService
import com.example.king_bob_nae.features.mykkilog.data.toDetailList
import com.example.king_bob_nae.features.mykkilog.data.toKkiLogList
import javax.inject.Inject

class MyKkiLogRepositoryImpl @Inject constructor(
    private val service: MyKkiLogService
) : MyKkiLogRepository {
    override suspend fun getMyKkiLog(): List<MyKkiLogThumbNail>? {
        return runCatching {
            service.getMyKkiLog().toKkiLogList()
        }.getOrNull()
    }

    override suspend fun getMyDetailKkiLog(): List<MyKkiLogThumbNail>? {
        return runCatching {
            service.getMyDetailKkiLog().toDetailList()
        }.getOrNull()
    }
}