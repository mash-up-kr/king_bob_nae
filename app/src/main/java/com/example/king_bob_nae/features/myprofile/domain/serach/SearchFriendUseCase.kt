package com.example.king_bob_nae.features.myprofile.domain.serach

import com.example.king_bob_nae.features.myprofile.domain.userfollow.RemoteGetUserFollow
import javax.inject.Inject

class SearchFriendUseCase @Inject constructor(private val remoteGetUserFollow: RemoteGetUserFollow) {
    suspend operator fun invoke(type: String, keyword: String?) = remoteGetUserFollow.remoteGetUserFollow(type = type, keyword = keyword)
}