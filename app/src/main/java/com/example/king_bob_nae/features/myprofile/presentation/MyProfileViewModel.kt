package com.example.king_bob_nae.features.myprofile.presentation

import androidx.lifecycle.ViewModel
import com.example.king_bob_nae.features.myprofile.domain.UserProfileUiState
import com.example.king_bob_nae.features.myprofile.domain.UserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(private val userProfileUseCase: UserProfileUseCase) : ViewModel() {

    private val _userProfileUiState: MutableStateFlow<UserProfileUiState> = MutableStateFlow(UserProfileUiState())
    val userProfileUiState = _userProfileUiState.asStateFlow()

    private val _userProfileScrapListUiState: MutableStateFlow<List<UserProfileUiState.ScrapedImage>> =
        MutableStateFlow(listOf(UserProfileUiState.ScrapedImage()))
    val userProfileScrapListUiState = _userProfileScrapListUiState.asStateFlow()

    suspend fun getUserProfile() {
        userProfileUseCase().apply {
            _userProfileUiState.value = this
            this.scrapList?.let {
                _userProfileScrapListUiState.value = it
            }
        }
    }

    fun updateScrapState(scrapedImage: UserProfileUiState.ScrapedImage) {
        _userProfileScrapListUiState.update {
            _userProfileScrapListUiState.value.map {
                if (it.id == scrapedImage.id) {
                    it.copy(clicked = !it.clicked)
                } else {
                    it
                }
            }
        }
    }
}
