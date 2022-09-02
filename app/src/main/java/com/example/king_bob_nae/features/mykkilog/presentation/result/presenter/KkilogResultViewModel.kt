package com.example.king_bob_nae.features.mykkilog.presentation.result.presenter

import androidx.lifecycle.ViewModel
import com.example.king_bob_nae.features.mykkilog.domain.deletekkilog.DeleteKkilogUseCase
import com.example.king_bob_nae.features.mykkilog.domain.getkkilog.GetKkilogUseCase
import com.example.king_bob_nae.features.mykkilog.domain.getkkilog.KkilogResultUiState
import com.example.king_bob_nae.features.mykkilog.domain.patchkkilog.PatchKkilogUseCase
import com.example.king_bob_nae.features.mykkilog.domain.postkkiloglike.PostKkilogLikeUseCase
import com.example.king_bob_nae.features.mykkilog.domain.postkkilogscrap.PostKkilogScrapUseCase
import com.example.king_bob_nae.features.mykkilog.domain.postkkilogunlike.PostKkilogUnLikeUseCase
import com.example.king_bob_nae.features.mykkilog.domain.postkkilogunscrap.PostKkilogUnScrapUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class KkilogResultViewModel @Inject constructor(
    private val deleteUseCase: DeleteKkilogUseCase,
    private val getKkilogUseCase: GetKkilogUseCase,
    private val patchKkilogUseCase: PatchKkilogUseCase,
    private val postKkilogLikeUseCase: PostKkilogLikeUseCase,
    private val postKkilogUnLikeUseCase: PostKkilogUnLikeUseCase,
    private val postKkilogScrapUseCase: PostKkilogScrapUseCase,
    private val postKkilogUnScrapUseCase: PostKkilogUnScrapUseCase,
) : ViewModel() {

    private val _kkilogResultUiState = MutableStateFlow(KkilogResultUiState())
    val kkilogResultUiState = _kkilogResultUiState.asStateFlow()

    suspend fun getSimpleKkilog(id: Int) {
        _kkilogResultUiState.value = getKkilogUseCase(id)
    }

    suspend fun postKkilogScrap(id: Int) {
        _kkilogResultUiState.value = _kkilogResultUiState.value.copy(isScraped = postKkilogScrapUseCase(id).isScraped)
    }

    suspend fun postKkilogUnScrap(id: Int) {
        _kkilogResultUiState.value = _kkilogResultUiState.value.copy(isScraped = postKkilogUnScrapUseCase(id).isScraped)
    }

    suspend fun postKkilogLike(id: Int) {
        _kkilogResultUiState.value =
            _kkilogResultUiState.value.copy(likeCount = _kkilogResultUiState.value.likeCount + 1, isLiked = postKkilogLikeUseCase(id).isLike)
    }

    suspend fun postKkilogUnLike(id: Int) {
        _kkilogResultUiState.value =
            _kkilogResultUiState.value.copy(likeCount = _kkilogResultUiState.value.likeCount - 1, isLiked = postKkilogUnLikeUseCase(id).isLike)
    }

}
