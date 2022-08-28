package com.example.king_bob_nae.features.myprofile.domain.userfriend.unfollow

import javax.inject.Inject

class UserFriendUnFollowUseCase @Inject constructor(private val remotePostUserFriendUnFollow: RemotePostUserFriendUnFollow) {
    suspend operator fun invoke(id: Int) = remotePostUserFriendUnFollow.postUserFriendUnFollow(id)
}
