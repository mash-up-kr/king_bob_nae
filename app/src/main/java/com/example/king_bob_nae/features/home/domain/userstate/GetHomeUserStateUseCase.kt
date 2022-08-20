package com.example.king_bob_nae.features.home.domain.userstate

import javax.inject.Inject

class GetHomeUserStateUseCase @Inject constructor(private val remoteGetHomeUserStatus: RemoteGetHomeUserStatus) {
    suspend operator fun invoke() = remoteGetHomeUserStatus.getUserInformation()
}
