package com.example.king_bob_nae.features.home.domain.freindlist

import javax.inject.Inject

class GetFriendListUseCase @Inject constructor(
    private val remoteGetFriendList: RemoteGetFriendList
) {
    suspend operator fun invoke() = remoteGetFriendList.getFriendList()
}
