package com.example.king_bob_nae.features.mykkilog.presentation.result.domain.getkkilog

data class KkilogResultUiState(
    val foodImageList: List<String> = emptyList(),
    val title: String = "",
    val isScraped: Boolean = false,
    val isLiked: Boolean = false,
    val updatedAt: String = "",
    val kick: String = "",
    val likeCount: Int = 0,
)
