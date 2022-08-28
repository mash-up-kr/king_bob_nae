package com.example.king_bob_nae.features.myprofile.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.king_bob_nae.features.myprofile.domain.userfollow.UserFollowUseCase
import com.example.king_bob_nae.features.myprofile.domain.userfollow.UsersFollowUiState
import com.example.king_bob_nae.features.myprofile.domain.userfriend.follow.UserFriendFollowUseCase
import com.example.king_bob_nae.features.myprofile.domain.userfriend.unfollow.UserFriendUnFollowUseCase
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
    private val userFriendFollow: UserFriendFollowUseCase,
    private val userFriendUnFollow: UserFriendUnFollowUseCase,
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
        kotlin.runCatching {
            userFriendUnFollow(item.id)
        }.onSuccess {
            Log.d("tjrwn", "doUnFollow: 성공 와유 ")
            updateFollowState(item.copy(following = false))
        }.onFailure {
            Log.d("tjrwn", "doUnFollow: 실패 와유 ${it.message}")
            updateFollowState(item.copy(following = true))
        }
    }

    suspend fun doFollow(item: UsersFollowUiState) {
        kotlin.runCatching {
            userFriendFollow(item.id)
        }.onSuccess {
            Log.d("tjrwn", "doFollow: 성공 와유")
            updateFollowState(item.copy(following = true))
        }.onFailure {
            Log.d("tjrwn", "doFollow: 실패 와유")
            updateFollowState(item.copy(following = false))
        }
    }

    fun updateFollowState(item: UsersFollowUiState) {
        _followListUiState.update {
            Log.d("tjrwn", "updateFollowState1 :${_followListUiState.value} ")
            _followListUiState.value.map { uiState ->
                if (uiState.id == item.id) {
                    item
                } else {
                    uiState
                }
            }
        }
        Log.d("tjrwn", "updateFollowState2 :${_followListUiState.value} ")
    }


}
