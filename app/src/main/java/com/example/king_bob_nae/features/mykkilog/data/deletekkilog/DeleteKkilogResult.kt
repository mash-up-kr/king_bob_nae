package com.example.king_bob_nae.features.mykkilog.data.deletekkilog

import com.example.king_bob_nae.features.intro.data.dto.ErrorDto
import com.google.gson.annotations.SerializedName

data class DeleteKkilogResult(
    @SerializedName("error")
    val error: ErrorDto?,
    @SerializedName("data")
    val data: Int?,
)
