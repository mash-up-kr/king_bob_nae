package com.example.king_bob_nae.features.create.detail.presentaion.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.king_bob_nae.features.create.detail.domain.GetDetailKkiLogResultUseCase
import com.example.king_bob_nae.features.create.detail.domain.model.DetailKkiLogResult
import com.example.king_bob_nae.utils.NLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailKkiLogResultViewModel @Inject constructor(
    private val getDetailKkiLogResultUseCase: GetDetailKkiLogResultUseCase
) : ViewModel() {

    var userId = 0

    private val _showToastMessage: MutableSharedFlow<String> =
        MutableSharedFlow()
    val showToastMessage = _showToastMessage.asSharedFlow()

    private val _detailKkiLogResult = MutableStateFlow(DetailKkiLogResult())
    val detailKkiLogResult = _detailKkiLogResult.asStateFlow()

    fun setKkiLogUserId(userId: Int) {
        this.userId = userId
        fetchDetailKkiLogResult(userId)
    }

    private fun fetchDetailKkiLogResult(userId: Int) {
        viewModelScope.launch {
            getDetailKkiLogResultUseCase(userId)
                .catch { e ->
                    NLog.d("kelly", e.message.toString())
                    _showToastMessage.emit("결과 불러오기 실패")
                }.collect {
                    _detailKkiLogResult.emit(it)
                }
        }
    }
}
