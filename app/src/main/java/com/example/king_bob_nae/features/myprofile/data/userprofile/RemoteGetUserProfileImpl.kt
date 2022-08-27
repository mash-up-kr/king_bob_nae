package com.example.king_bob_nae.features.myprofile.data.userprofile

import com.example.king_bob_nae.features.myprofile.data.ProfileApi
import com.example.king_bob_nae.features.myprofile.domain.userprofile.RemoteGetUserProfile
import com.example.king_bob_nae.features.myprofile.domain.userprofile.UserProfileUiState
import javax.inject.Inject

class RemoteGetUserProfileImpl @Inject constructor(private val profileApi: ProfileApi) : RemoteGetUserProfile {
    override suspend fun remoteGetUserProfile(): UserProfileUiState = profileApi.getUserProfile().toUserProfileUiState()
}
