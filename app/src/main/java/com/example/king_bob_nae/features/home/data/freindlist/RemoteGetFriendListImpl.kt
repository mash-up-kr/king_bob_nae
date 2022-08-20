package com.example.king_bob_nae.features.home.data.freindlist

import com.example.king_bob_nae.features.home.data.HomeApi
import com.example.king_bob_nae.features.home.data.toUserListItem
import com.example.king_bob_nae.features.home.domain.freindlist.RemoteGetFriendList
import com.example.king_bob_nae.features.home.domain.freindlist.UserListItem
import javax.inject.Inject

class RemoteGetFriendListImpl @Inject constructor(private val homeApi: HomeApi) : RemoteGetFriendList {
    override suspend fun getFriendList(): List<UserListItem>? {
        return kotlin.runCatching {
            homeApi.getFriendsList().toUserListItem()
        }.getOrNull()
    }
}
