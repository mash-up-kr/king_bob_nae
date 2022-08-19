package com.example.king_bob_nae.features.home.data.userstate

import com.example.king_bob_nae.features.home.data.HomeApi
import com.example.king_bob_nae.features.home.data.toHomeState
import com.example.king_bob_nae.features.home.domain.userstate.HomeUserState
import com.example.king_bob_nae.features.home.domain.userstate.RemoteGetHomeUserStatus
import javax.inject.Inject

class RemoteGetHomeUserStatusImpl @Inject constructor(
    private val homeStatus: HomeApi
) : RemoteGetHomeUserStatus {
    override suspend fun getUserInformation(): HomeUserState {
        return homeStatus.getHomeStatus().data.toHomeState()
    }
}
