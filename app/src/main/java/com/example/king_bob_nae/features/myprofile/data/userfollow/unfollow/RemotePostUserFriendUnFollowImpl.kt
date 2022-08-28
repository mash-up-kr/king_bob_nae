package com.example.king_bob_nae.features.myprofile.data.userfollow.unfollow

import com.example.king_bob_nae.features.myprofile.data.ProfileApi
import com.example.king_bob_nae.features.myprofile.domain.userfriend.UserFriendUiState
import com.example.king_bob_nae.features.myprofile.domain.userfriend.unfollow.RemotePostUserFriendUnFollow
import javax.inject.Inject

class RemotePostUserFriendUnFollowImpl @Inject constructor(private val profileApi: ProfileApi) : RemotePostUserFriendUnFollow {
    override suspend fun postUserFriendUnFollow(id: Int): UserFriendUiState = profileApi.postUserFriendUnFollow(id).data.toUserFriendUiState()

}
