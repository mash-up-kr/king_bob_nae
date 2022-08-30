package com.example.king_bob_nae.features.home.data.freindsstatus

import com.example.king_bob_nae.features.home.data.HomeApi
import com.example.king_bob_nae.features.home.data.toHomeState
import com.example.king_bob_nae.features.home.domain.friendsStatus.RemoteGetFriendsUserStatus
import com.example.king_bob_nae.features.home.domain.userstate.HomeUserState
import javax.inject.Inject

class RemoteGetFriendsStatusImpl @Inject constructor(
    private val homeStatus: HomeApi
) : RemoteGetFriendsUserStatus {
    override suspend fun getFriendsInformation(userId: Int): HomeUserState {
        return homeStatus.getFriendsStatus(userId).data.toHomeState()
    }
}
