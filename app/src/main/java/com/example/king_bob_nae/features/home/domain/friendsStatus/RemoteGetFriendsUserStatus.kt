package com.example.king_bob_nae.features.home.domain.friendsStatus

import com.example.king_bob_nae.features.home.domain.userstate.HomeUserState

interface RemoteGetFriendsUserStatus {
    suspend fun getFriendsInformation(userId: Int): HomeUserState
}
