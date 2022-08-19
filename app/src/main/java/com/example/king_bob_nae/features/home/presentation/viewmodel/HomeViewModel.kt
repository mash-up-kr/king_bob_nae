package com.example.king_bob_nae.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.king_bob_nae.features.home.domain.freindlist.GetFriendListUseCase
import com.example.king_bob_nae.features.home.domain.freindlist.UserListItem
import com.example.king_bob_nae.features.home.domain.userstate.GetHomeUserStateUseCase
import com.example.king_bob_nae.features.home.domain.userstate.HomeUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeStatus: GetHomeUserStateUseCase,
    private val friendList: GetFriendListUseCase
) : ViewModel() {
    private val _homeUserState: MutableStateFlow<HomeUserState> = MutableStateFlow<HomeUserState>(HomeUserState())
    val userList = _homeUserState.asStateFlow()

    private val _homeUserFriendList: MutableStateFlow<List<UserListItem>> =
        MutableStateFlow<List<UserListItem>>(emptyList())
    val homeUserFriendList = _homeUserFriendList.asStateFlow()

    fun getHomeStatus() {
        viewModelScope.launch {
            _homeUserState.value = homeStatus()
        }
    }

    fun getFriendList() {
        viewModelScope.launch {
            friendList()?.let {
                _homeUserFriendList.value = it
            }
        }
    }
}
