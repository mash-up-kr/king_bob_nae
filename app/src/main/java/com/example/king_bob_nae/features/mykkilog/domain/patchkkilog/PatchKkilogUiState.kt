package com.example.king_bob_nae.features.mykkilog.domain.patchkkilog

data class PatchKkilogUiState(
    val foodImageList: List<String> = emptyList(),
    val title: String = "",
    val description: String = "",
    val isScraped: Boolean = false,
    val isLiked: Boolean = false,
    val updatedAt: String = "",
    val kick: String = "",
    val likeCount: Int = 0,
)
