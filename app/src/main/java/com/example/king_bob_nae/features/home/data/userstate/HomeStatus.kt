package com.example.king_bob_nae.features.home.data.userstate

import com.example.king_bob_nae.features.intro.data.dto.CHARACTER
import com.example.king_bob_nae.features.intro.data.dto.CharacterStatus
import com.example.king_bob_nae.features.intro.data.dto.ErrorDto
import com.google.gson.annotations.SerializedName

data class HomeStatusResult(
    @SerializedName("error")
    val error: ErrorDto?,
    @SerializedName("data")
    val data: HomeStatus
)

data class HomeStatus(
    @SerializedName("email")
    val email: String,
    @SerializedName("fullImageUrl")
    val fullImageUrl: String,
    @SerializedName("lastFeedAt")
    val lastFeedAt: String?,
    @SerializedName("logStats")
    val logStats: LogStats,
    @SerializedName("miniImageUrl")
    val miniImageUrl: String,
    @SerializedName("name")
    val nickName: String,
    @SerializedName("phrase")
    val phrase: String,
    @SerializedName("status")
    val status: CharacterStatus,
    @SerializedName("type")
    val type: CHARACTER
) {
    data class LogStats(
        @SerializedName("level")
        val level: Int,
        @SerializedName("progress")
        val progress: Int,
        @SerializedName("max")
        val max: Int,
        @SerializedName("today")
        val today: Int,
        @SerializedName("total")
        val total: Int
    )
}
