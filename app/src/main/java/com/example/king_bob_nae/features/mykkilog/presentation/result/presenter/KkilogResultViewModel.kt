package com.example.king_bob_nae.features.mykkilog.presentation.result.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.king_bob_nae.features.mykkilog.presentation.result.domain.deletekkilog.DeleteKkilogUseCase
import com.example.king_bob_nae.features.mykkilog.presentation.result.domain.getkkilog.GetKkilogUseCase
import com.example.king_bob_nae.features.mykkilog.presentation.result.domain.patchkkilog.PatchKkilogUseCase
import com.example.king_bob_nae.features.mykkilog.presentation.result.domain.postkkiloglike.PostKkilogLikeUseCase
import com.example.king_bob_nae.features.mykkilog.presentation.result.domain.postkkilogscrap.PostKkilogScrapUseCase
import com.example.king_bob_nae.features.mykkilog.presentation.result.domain.postkkilogunscrap.PostKkilogUnScrapUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KkilogResultViewModel @Inject constructor(
    private val deleteUseCase: DeleteKkilogUseCase,
    private val getKkilogUseCase: GetKkilogUseCase,
    private val patchKkilogUseCase: PatchKkilogUseCase,
    private val postKkilogLikeUseCase: PostKkilogLikeUseCase,
    private val postKkilogUnLikeUseCase: PostKkilogLikeUseCase,
    private val postKkilogScrapUseCase: PostKkilogScrapUseCase,
    private val postKkilogUnScrapUseCase: PostKkilogUnScrapUseCase,
) : ViewModel() {

    suspend fun test(case: Int, id: Int) {
        kotlin.runCatching {
            when (case) {
                1 -> getKkilogUseCase(id)
                2 -> patchKkilogUseCase(id) //requestBody 수정해야됨
                3 -> postKkilogLikeUseCase(id)
                4 -> postKkilogUnLikeUseCase(id)
                5 -> postKkilogScrapUseCase(id)
                6 -> postKkilogUnScrapUseCase(id)
                7 -> deleteUseCase(id)
                else -> Log.d("tjrwn", "test: 터짐 ")
            }
        }.onSuccess {
            Log.d("tjrwn", "test 성공: $it")
        }.onFailure {
            Log.d("tjrwn", "test 실패: $it ")
        }
    }
}
