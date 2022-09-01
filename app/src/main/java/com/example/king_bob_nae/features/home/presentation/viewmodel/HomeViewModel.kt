package com.example.king_bob_nae.features.home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.king_bob_nae.features.home.domain.freindlist.GetFriendListUseCase
import com.example.king_bob_nae.features.home.domain.freindlist.UserListItem
import com.example.king_bob_nae.features.home.domain.friendsStatus.GetFriendsStatusUseCase
import com.example.king_bob_nae.features.home.domain.friendshome.GetAllKkilogUseCase
import com.example.king_bob_nae.features.home.domain.friendshome.KkilogState
import com.example.king_bob_nae.features.home.domain.levelup.HomeLevelUpUseCase
import com.example.king_bob_nae.features.home.domain.userstate.GetHomeUserStateUseCase
import com.example.king_bob_nae.features.home.domain.userstate.HomeUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeStatus: GetHomeUserStateUseCase,
    private val friendsStatus: GetFriendsStatusUseCase,
    private val friendList: GetFriendListUseCase,
    private val levelUp: HomeLevelUpUseCase,
    private val getAllKkilog: GetAllKkilogUseCase,
) : ViewModel() {
    private val _homeUserState: MutableStateFlow<HomeUserState> =
        MutableStateFlow<HomeUserState>(HomeUserState())
    val userList = _homeUserState.asStateFlow()

    private val _homeFriendsStatus: MutableStateFlow<HomeUserState> =
        MutableStateFlow<HomeUserState>(HomeUserState())
    val homeFriendsStatus = _homeFriendsStatus.asStateFlow()

    private val _homeUserFriendList: MutableStateFlow<List<UserListItem>> =
        MutableStateFlow<List<UserListItem>>(emptyList())
    val homeUserFriendList = _homeUserFriendList.asStateFlow()

    private val _goFriendsHomeFragmentEvent = MutableSharedFlow<Int>()
    val goFriendsHomeFragmentEvent = _goFriendsHomeFragmentEvent.asSharedFlow()

    private val _goHomeFragmentEvent = MutableSharedFlow<Int>()
    val goHomeFragmentEvent = _goHomeFragmentEvent.asSharedFlow()

    private val _allKkilogList = MutableStateFlow<List<KkilogState>>(listOf())
    val allKkilogList = _allKkilogList.asStateFlow()

    val isTutorial = MutableStateFlow<Boolean>(true)

    private var userId: Int = 0

    fun setSelectedUserId(userId: Int) {
        this.userId = userId
        getFriendsStatus()
    }

    fun getHomeStatus() {
        viewModelScope.launch {
            _homeUserState.value = homeStatus()
        }
    }

    fun getFriendList() {
        viewModelScope.launch {
            friendList()?.let {
                _homeUserFriendList.value =
                    listOf(
                        UserListItem(
                            -1,
                            homeStatus().smallImageUrl,
                            homeStatus().userNickName
                        )
                    ) + it
            }
        }
    }

    fun onFriendsItemClick(item: UserListItem) {
        viewModelScope.launch {
            if (item.id < 0) {
                _goHomeFragmentEvent.emit(item.id)
            } else {
                _goFriendsHomeFragmentEvent.emit(item.id)
            }
        }
    }

    fun getFriendsStatus() {
        viewModelScope.launch {
            _homeFriendsStatus.value = friendsStatus(userId)
            getUserAllKkilogList()
        }
    }

    fun postLevelUp() {
        viewModelScope.launch {
            kotlin.runCatching {
                levelUp()
            }.onFailure {
                // doNothing
            }
        }
    }

    fun getUserAllKkilogList() {
        viewModelScope.launch {
            kotlin.runCatching {
                getAllKkilog(userId)
            }.onSuccess { list ->
                Log.d("tjrwn", "getUserAllKkilogList: $list 오긴함")
                list?.let {
                    _allKkilogList.value = list
                }
            }.onFailure {
                Log.d("tjrwn", "getUserAllKkilogList:실패입니당 ${it.message} ")
                // 에러입니다~
            }
        }
    }
}
