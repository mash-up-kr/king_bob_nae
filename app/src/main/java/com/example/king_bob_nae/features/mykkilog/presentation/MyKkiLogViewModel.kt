package com.example.king_bob_nae.features.mykkilog.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.king_bob_nae.features.mykkilog.data.MyKkiLogThumbNail
import com.example.king_bob_nae.features.mykkilog.domain.GetMyDetailKkiLogUseCase
import com.example.king_bob_nae.features.mykkilog.domain.GetMyKkiLogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyKkiLogViewModel @Inject constructor(
    private val getMyKkiLogUseCase: GetMyKkiLogUseCase,
    private val getMyDetailKkiLogUseCase: GetMyDetailKkiLogUseCase
) : ViewModel() {
    private val _myKkiLog = MutableStateFlow<List<MyKkiLogThumbNail>?>(emptyList())
    val myKkiLog = _myKkiLog.asStateFlow()

    private val _myDetailKkiLog = MutableStateFlow<List<MyKkiLogThumbNail>?>(emptyList())
    val myDetailKkiLog = _myDetailKkiLog.asStateFlow()

    fun getKkiLogList() {
        viewModelScope.launch {
            _myKkiLog.value = getMyKkiLogUseCase()
        }
    }

    fun getDetailKkiLog() {
        viewModelScope.launch {
            _myDetailKkiLog.value = getMyDetailKkiLogUseCase()
        }
    }
}