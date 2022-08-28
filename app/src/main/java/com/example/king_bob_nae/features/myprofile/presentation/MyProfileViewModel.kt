package com.example.king_bob_nae.features.myprofile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.king_bob_nae.features.myprofile.domain.serach.SearchFriendUseCase
import com.example.king_bob_nae.features.myprofile.domain.userfollow.UserFollowUseCase
import com.example.king_bob_nae.features.myprofile.domain.userfollow.UsersFollowUiState
import com.example.king_bob_nae.features.myprofile.domain.userfriend.follow.UserFriendFollowUseCase
import com.example.king_bob_nae.features.myprofile.domain.userfriend.unfollow.UserFriendUnFollowUseCase
import com.example.king_bob_nae.features.myprofile.domain.userprofile.UserProfileUiState
import com.example.king_bob_nae.features.myprofile.domain.userprofile.UserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val userProfileUseCase: UserProfileUseCase,
    private val userFollowUseCase: UserFollowUseCase,
    private val userFriendFollow: UserFriendFollowUseCase,
    private val userFriendUnFollow: UserFriendUnFollowUseCase,
    private val searchFriendUseCase: SearchFriendUseCase
) :
    ViewModel() {

    private val _userProfileUiState: MutableStateFlow<UserProfileUiState> =
        MutableStateFlow(UserProfileUiState())
    val userProfileUiState = _userProfileUiState.asStateFlow()

    private val _userProfileScrapListUiState: MutableStateFlow<List<UserProfileUiState.ScrapedImage>> =
        MutableStateFlow(listOf(UserProfileUiState.ScrapedImage()))
    val userProfileScrapListUiState = _userProfileScrapListUiState.asStateFlow()

    private val _followListUiState: MutableStateFlow<List<UsersFollowUiState>> =
        MutableStateFlow(listOf(UsersFollowUiState()))
    val followListUiState = _followListUiState.asStateFlow()

    private val _searchFriendState: MutableStateFlow<UsersFollowUiState> =
        MutableStateFlow(UsersFollowUiState())
    val searchFriendState = _searchFriendState.asStateFlow()

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

    suspend fun searchFriend(type: String, keyword: String, onSuccess: () -> Unit) {
        kotlin.runCatching {
            searchFriendUseCase(type, keyword)
        }.onSuccess { result ->
            if (result.isNotEmpty()) {
                _searchFriendState.value = result.first()
                onSuccess()
            }
        }
    }

    suspend fun getUserFollow(type: String, keyword: String? = null) {
        kotlin.runCatching {
            userFollowUseCase(type, keyword)
        }.onSuccess {
            if (it.isNotEmpty()) {
                _followListUiState.value = it
            }
        }
    }

    fun friendsDoUnFollow(item: UsersFollowUiState?) {
        viewModelScope.launch {
            kotlin.runCatching {
                item?.id?.let {
                    userFriendUnFollow(it)
                }
            }.onSuccess {
                _searchFriendState.value = _searchFriendState.value.copy(following = false)
            }.onFailure {
                _searchFriendState.value = _searchFriendState.value.copy(following = true)
            }
        }
    }

    fun friendsDoFollow(item: UsersFollowUiState?) {
        viewModelScope.launch {
            kotlin.runCatching {
                item?.id?.let {
                    userFriendFollow(it)
                }
            }.onSuccess {
                _searchFriendState.value = _searchFriendState.value.copy(following = true)
            }.onFailure {
                _searchFriendState.value = _searchFriendState.value.copy(following = false)
            }
        }
    }

    suspend fun doUnFollow(item: UsersFollowUiState) {
        kotlin.runCatching {
            userFriendUnFollow(item.id)
        }.onSuccess {
            updateFollowState(item.copy(following = false))
        }.onFailure {
            updateFollowState(item.copy(following = true))
        }
    }

    suspend fun doFollow(item: UsersFollowUiState) {
        kotlin.runCatching {
            userFriendFollow(item.id)
        }.onSuccess {
            updateFollowState(item.copy(following = true))
        }.onFailure {
            updateFollowState(item.copy(following = false))
        }
    }

    fun updateFollowState(item: UsersFollowUiState) {
        _followListUiState.update {
            _followListUiState.value.map { uiState ->
                if (uiState.id == item.id) {
                    item
                } else {
                    uiState
                }
            }
        }
    }


}
