package com.example.king_bob_nae.features.home.data.levelup

import com.example.king_bob_nae.features.home.data.HomeApi
import com.example.king_bob_nae.features.home.domain.levelup.HomeLevelUp
import javax.inject.Inject

class HomeLevelUpImpl @Inject constructor(private val homeApi: HomeApi) : HomeLevelUp {
    override suspend fun postLevelUp() = homeApi.postLevelUp()
}
