package com.example.king_bob_nae.features.home.data.friendshome

import com.example.king_bob_nae.features.home.data.HomeApi
import com.example.king_bob_nae.features.home.data.toKkilogState
import com.example.king_bob_nae.features.home.domain.friendshome.RemoteGetAllkkilog
import javax.inject.Inject

class RemoteGetAllKkilogImpl @Inject constructor(private val homeApi: HomeApi) : RemoteGetAllkkilog {
    override suspend fun getAllKkilog(userId: Int) = homeApi.getUserAllKkilog(userId).data?.toKkilogState()
}
