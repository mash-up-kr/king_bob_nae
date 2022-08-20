package com.example.king_bob_nae.features.home.domain.freindlist

interface RemoteGetFriendList {
    suspend fun getFriendList(): List<UserListItem>?
}
