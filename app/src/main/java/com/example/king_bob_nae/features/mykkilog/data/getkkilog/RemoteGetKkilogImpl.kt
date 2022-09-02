package com.example.king_bob_nae.features.mykkilog.data.getkkilog

import android.util.Log
import com.example.king_bob_nae.features.mykkilog.data.SimpleKkilogApi
import com.example.king_bob_nae.features.mykkilog.data.toKkilogResultUiState
import com.example.king_bob_nae.features.mykkilog.domain.getkkilog.KkilogResultUiState
import com.example.king_bob_nae.features.mykkilog.domain.getkkilog.RemoteGetKkilog
import javax.inject.Inject

class RemoteGetKkilogImpl @Inject constructor(private val simpleKkilogApi: SimpleKkilogApi) : RemoteGetKkilog {
    override suspend fun getKkilog(id: Int): KkilogResultUiState {
        Log.d("tjrwn", "getKkilog: ${simpleKkilogApi.getKkilog(id).toKkilogResultUiState()}")
        return simpleKkilogApi.getKkilog(id).toKkilogResultUiState()
    }
}
