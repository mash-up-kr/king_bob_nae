package com.example.king_bob_nae.features.myprofile.domain

import javax.inject.Inject

class UserProfileUseCase @Inject constructor(private val remoteGetUserProfile: RemoteGetUserProfile) {
    suspend operator fun invoke() = remoteGetUserProfile.remoteGetUserProfile()
}
