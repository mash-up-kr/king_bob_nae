package com.example.king_bob_nae.features.home.domain.userstate

import androidx.annotation.Keep

@Keep
data class HomeUserState(
    val date: String = "",
    val todayKkirokCount: String = "",
    val userNickName: String = "",
    val phrase: String = "",
    val max: Int = 0,
    val progressBar: Int = 0,
    val level: String = "",
    val levelTotalKkirokCount: String = "",
    val largeImageUrl: String = "",
    val smallImageUrl: String = "",
    val totalKkilogCount: Int = 0,
)
