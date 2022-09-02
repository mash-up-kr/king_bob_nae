package com.example.king_bob_nae.features.create.detail.presentaion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.king_bob_nae.features.create.detail.data.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailKkiLogSharedViewModel @Inject constructor() : ViewModel() {

    private val _recipePair = MutableStateFlow(0 to listOf<Recipe>())
    val recipePair = _recipePair.asStateFlow()

    val currentPosition = MutableStateFlow<Int>(0)

    fun setKkiLogRecipeList(pair: Pair<Int, List<Recipe>>) {
        viewModelScope.launch {
            _recipePair.value = pair
        }
    }
}
