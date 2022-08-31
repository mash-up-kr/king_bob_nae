package com.example.king_bob_nae.features.home.domain.levelup

import com.example.king_bob_nae.features.home.data.levelup.LevelUpResult

interface HomeLevelUp {
    suspend fun postLevelUp(): LevelUpResult
}
