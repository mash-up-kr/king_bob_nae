package com.example.king_bob_nae.features.myprofile.domain.userprofile

data class UserProfileUiState(
    val profileImageUrl: String = "",
    val level: String = "",
    val nickName: String = "",
    val progressbarPercent: Int = 0,
    val totalKkilogCount: String = "",
    val totalKKini: String = "",
    val following: String = "0",
    val follower: String = "0",
    val scrapList: List<ScrapedImage>? = listOf(),
    val email: String = "",
) {
    data class ScrapedImage(
        val id: String = "",
        val clicked: Boolean = true,
        val title: String = "",
        val type: String = "",
        val imageUrl: String = "",
    )
}
