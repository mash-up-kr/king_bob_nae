package com.example.king_bob_nae.features.myprofile.data.userfollow.follow

import com.example.king_bob_nae.features.myprofile.data.ProfileApi
import com.example.king_bob_nae.features.myprofile.domain.userfriend.UserFriendUiState
import com.example.king_bob_nae.features.myprofile.domain.userfriend.follow.RemotePostUserFriendFollow
import javax.inject.Inject

class RemotePostUserFriendFollowImpl @Inject constructor(private val profileApi: ProfileApi) : RemotePostUserFriendFollow {
    override suspend fun postUserFriendFollow(id: Int): UserFriendUiState = profileApi.postUserFriendFollow(id).data.toUserFriendUiState()
}
