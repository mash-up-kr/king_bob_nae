package com.example.king_bob_nae.features.home.domain.userstate

import androidx.annotation.Keep

@Keep
data class HomeUserState(
    val date: String = "",
    val todayKkirokCount: String = "",
    val userNickName: String = "",
    val phrase: String = "",
    val progressBarPercent: Int = 0,
    val level: String = "",
    val totalKkirokCount: String = "",
    val largeImageUrl: String = "",
    val smallImageUrl: String = ""
)
