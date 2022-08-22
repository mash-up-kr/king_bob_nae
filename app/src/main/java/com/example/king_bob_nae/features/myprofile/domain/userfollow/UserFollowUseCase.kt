package com.example.king_bob_nae.features.myprofile.domain.userfollow

import javax.inject.Inject

class UserFollowUseCase @Inject constructor(private val remoteGetUserFollow: RemoteGetUserFollow) {
    suspend operator fun invoke(type: String, keyword: String?) = remoteGetUserFollow.remoteGetUserFollow(type = type, keyword = keyword)
}
