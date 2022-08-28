package com.example.king_bob_nae.features.myprofile.data.userfollow.getuserfollow

import com.example.king_bob_nae.features.myprofile.data.ProfileApi
import com.example.king_bob_nae.features.myprofile.domain.userfollow.RemoteGetUserFollow
import javax.inject.Inject

class RemoteGetUserFollowImpl @Inject constructor(private val profileApi: ProfileApi) : RemoteGetUserFollow {
    override suspend fun remoteGetUserFollow(type: String, keyword: String?) = profileApi.getUserFollowList(type, keyword).toUserFollowUiState()
}
