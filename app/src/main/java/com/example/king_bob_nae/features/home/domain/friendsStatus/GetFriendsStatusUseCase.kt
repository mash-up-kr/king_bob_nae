package com.example.king_bob_nae.features.home.domain.friendsStatus

import javax.inject.Inject

class GetFriendsStatusUseCase @Inject constructor(private val remoteGetFriendsUserStatus: RemoteGetFriendsUserStatus) {
    suspend operator fun invoke(userId: Int) =
        remoteGetFriendsUserStatus.getFriendsInformation(userId)
}
