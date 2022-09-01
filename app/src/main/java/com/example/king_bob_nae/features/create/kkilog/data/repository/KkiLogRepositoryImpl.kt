package com.example.king_bob_nae.features.create.kkilog.data.repository

import android.util.Log
import com.example.king_bob_nae.features.create.kkilog.data.dto.UpLoadDto
import com.example.king_bob_nae.features.create.kkilog.data.dto.stringToNonNullableRequestBody
import com.example.king_bob_nae.features.create.kkilog.data.dto.stringToNullableRequestBody
import com.example.king_bob_nae.features.create.kkilog.data.service.KkiLogService
import com.example.king_bob_nae.utils.toMultipartBody
import javax.inject.Inject

class KkiLogRepositoryImpl @Inject constructor(
    private val service: KkiLogService
) : KkiLogRepository {
    override suspend fun postKkiLog(dto: UpLoadDto) {
        runCatching {
            val multipartList = dto.images.map {
                it.toMultipartBody(it)!!
            }
            Log.d("multipart", "postKkiLog: $multipartList")
            service.postKkiLog(
                images = multipartList,
                title = dto.title.stringToNonNullableRequestBody(),
                description = dto.description?.stringToNullableRequestBody(),
                kick = dto.kick?.stringToNullableRequestBody()
            )
        }.onFailure {
            Log.e("upload", "postKkiLog: $it")
        }
    }
}