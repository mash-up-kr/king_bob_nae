package com.example.king_bob_nae.features.mykkilog.data.repository

import com.example.king_bob_nae.features.mykkilog.data.MyKkiLogThumbNail

interface MyKkiLogRepository {
    suspend fun getMyKkiLog(): List<MyKkiLogThumbNail>?

    suspend fun getMyDetailKkiLog(): List<MyKkiLogThumbNail>?
}