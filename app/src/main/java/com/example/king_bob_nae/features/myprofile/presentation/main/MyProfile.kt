package com.example.king_bob_nae.features.myprofile.presentation.main

import com.example.king_bob_nae.features.intro.data.dto.CHARACTER
import com.example.king_bob_nae.features.intro.data.dto.CharacterStatus

data class MyProfile(
    val name: String = "",
    val level: Int = 1,
    val currentKkilogCount: Int = 0,
    val follower: Int = 0,
    val following: Int = 0,
    val scrapList: List<String> = listOf(),
    val characterType: CHARACTER = CHARACTER.BROCCOLI,
    val characterStatus: CharacterStatus = CharacterStatus.HAPPY,
)
