package com.example.king_bob_nae.features.create.kkilog.data.repository

import android.util.Log
import com.example.king_bob_nae.features.create.kkilog.data.dto.UpLoadDto
import com.example.king_bob_nae.features.create.kkilog.data.dto.listToMultiPartBody
import com.example.king_bob_nae.features.create.kkilog.data.dto.stringToMultiPartBody
import com.example.king_bob_nae.features.create.kkilog.data.service.KkiLogService
import javax.inject.Inject

class KkiLogRepositoryImpl @Inject constructor(
    private val service: KkiLogService
) : KkiLogRepository {
    override suspend fun postKkiLog(dto: UpLoadDto) {
        runCatching {
            val multipartList = dto.images.listToMultiPartBody()
            service.postKkiLog(
                images = multipartList,
                title = dto.title.stringToMultiPartBody("title"),
                description = dto.description?.stringToMultiPartBody("description"),
                kick = dto.kick?.stringToMultiPartBody("kick")
            )
        }.onFailure {
            Log.e("upload", "postKkiLog: $it")
        }
    }
}