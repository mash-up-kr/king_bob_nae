package com.example.king_bob_nae.features.home.data.levelup

import com.example.king_bob_nae.features.intro.data.dto.ErrorDto
import com.google.gson.annotations.SerializedName

data class LevelUpResult(
    @SerializedName("error")
    val error: ErrorDto?,
    @SerializedName("data")
    val data: LevelUp,
)

data class LevelUp(
    val result: Boolean,
)
