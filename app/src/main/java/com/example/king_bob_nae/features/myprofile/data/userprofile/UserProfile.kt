package com.example.king_bob_nae.features.myprofile.data


import com.example.king_bob_nae.features.intro.data.dto.CHARACTER
import com.example.king_bob_nae.features.intro.data.dto.ErrorDto
import com.google.gson.annotations.SerializedName

data class UserProfileResult(
    val error: ErrorDto,
    val data: UserProfile,
)

data class UserProfile(
    @SerializedName("email")
    val email: String,
    @SerializedName("followerCount")
    val followerCount: Int,
    @SerializedName("followingCount")
    val followingCount: Int,
    @SerializedName("fullImageUrl")
    val fullImageUrl: String,
    @SerializedName("logStats")
    val logStats: LogStats,
    @SerializedName("miniImageUrl")
    val miniImageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("scrappedLogs")
    val scrappedLogs: List<ScrappedLog>,
    @SerializedName("type")
    val type: CHARACTER,
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
        val total: Int,
    )

    data class ScrappedLog(
        @SerializedName("id")
        val id: Int,
        @SerializedName("image")
        val image: Image,
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: String,
    ) {
        data class Image(
            @SerializedName("original")
            val original: String,
            @SerializedName("w1024")
            val w1024: String,
            @SerializedName("w256")
            val w256: String,
        )
    }
}
