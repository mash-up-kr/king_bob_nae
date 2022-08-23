package com.example.king_bob_nae.features.myprofile.presentation

import androidx.lifecycle.ViewModel
import com.example.king_bob_nae.features.myprofile.domain.userfollow.UserFollowUseCase
import com.example.king_bob_nae.features.myprofile.domain.userfollow.UsersFollowUiState
import com.example.king_bob_nae.features.myprofile.domain.userprofile.UserProfileUiState
import com.example.king_bob_nae.features.myprofile.domain.userprofile.UserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val userProfileUseCase: UserProfileUseCase,
    private val userFollowUseCase: UserFollowUseCase,
) :
    ViewModel() {

    private val _userProfileUiState: MutableStateFlow<UserProfileUiState> = MutableStateFlow(UserProfileUiState())
    val userProfileUiState = _userProfileUiState.asStateFlow()

    private val _userProfileScrapListUiState: MutableStateFlow<List<UserProfileUiState.ScrapedImage>> =
        MutableStateFlow(listOf(UserProfileUiState.ScrapedImage()))
    val userProfileScrapListUiState = _userProfileScrapListUiState.asStateFlow()

    private val _followListUiState: MutableStateFlow<List<UsersFollowUiState>> = MutableStateFlow(listOf(UsersFollowUiState()))
    val followListUiState = _followListUiState.asStateFlow()

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
                    it.copy(clicked = scrapedImage.clicked)
                } else {
                    it
                }
            }
        }
    }

    suspend fun getUserFollow(type: String, keyword: String? = null) {
        userFollowUseCase(type, keyword).apply {
            _followListUiState.value = this
        }
    }

    suspend fun doUnFollow(item: UsersFollowUiState) {

    }

    suspend fun doFollow(item: UsersFollowUiState) {

    }


}
