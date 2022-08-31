package com.example.king_bob_nae.features.home.domain.levelup

import javax.inject.Inject

class HomeLevelUpUseCase @Inject constructor(private val homeLevelUp: HomeLevelUp) {
    suspend operator fun invoke() = homeLevelUp.postLevelUp()
}
