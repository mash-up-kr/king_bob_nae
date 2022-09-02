package com.example.king_bob_nae.features.mykkilog.presentation.result.data

import com.example.king_bob_nae.features.mykkilog.presentation.result.data.getkkilog.GetKkilogResult
import com.example.king_bob_nae.features.mykkilog.presentation.result.data.patchkkilog.PatchKkilogResult
import com.example.king_bob_nae.features.mykkilog.presentation.result.domain.getkkilog.KkilogResultUiState
import com.example.king_bob_nae.features.mykkilog.presentation.result.domain.patchkkilog.PatchKkilogUiState

fun GetKkilogResult.toKkilogResultUiState() = KkilogResultUiState(foodImageList = this.data.images.map { it.w1024 },
    title = this.data.title,
    isScraped = this.data.isScrapped,
    isLiked = this.data.like.isLike,
    updatedAt = this.data.updatedAt,
    kick = this.data.kick,
    likeCount = this.data.like.count
)

fun PatchKkilogResult.toPatchKkilogUiState() = PatchKkilogUiState(foodImageList = this.data.images.map { it.w1024 },
    title = this.data.title,
    isScraped = this.data.isScrapped,
    isLiked = this.data.like.isLike,
    updatedAt = this.data.updatedAt,
    kick = this.data.kick,
    likeCount = this.data.like.count
)
