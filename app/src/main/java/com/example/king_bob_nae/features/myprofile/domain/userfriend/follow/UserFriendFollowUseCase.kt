package com.example.king_bob_nae.features.myprofile.domain.userfriend.follow

import javax.inject.Inject

class UserFriendFollowUseCase @Inject constructor(private val remotePostUserFriendFollow: RemotePostUserFriendFollow) {
    suspend operator fun invoke(id: Int) = remotePostUserFriendFollow.postUserFriendFollow(id)
}
