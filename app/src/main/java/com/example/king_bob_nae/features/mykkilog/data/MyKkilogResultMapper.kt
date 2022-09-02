package com.example.king_bob_nae.features.mykkilog.data

import com.example.king_bob_nae.features.mykkilog.data.getkkilog.GetKkilogResult
import com.example.king_bob_nae.features.mykkilog.data.patchkkilog.PatchKkilogResult
import com.example.king_bob_nae.features.mykkilog.domain.getkkilog.KkilogResultUiState
import com.example.king_bob_nae.features.mykkilog.domain.patchkkilog.PatchKkilogUiState

fun GetKkilogResult.toKkilogResultUiState() = KkilogResultUiState(foodImageList = this.data.images.map { it.w1024 },
    description = this.data.description,
    title = this.data.title,
    isScraped = this.data.isScrapped,
    isLiked = this.data.like.isLike,
    updatedAt = this.data.updatedAt,
    kick = this.data.kick,
    likeCount = this.data.like.count
)

fun PatchKkilogResult.toPatchKkilogUiState() = PatchKkilogUiState(foodImageList = this.data.images.map { it.w1024 },
    description = this.data.description,
    title = this.data.title,
    isScraped = this.data.isScrapped,
    isLiked = this.data.like.isLike,
    updatedAt = this.data.updatedAt,
    kick = this.data.kick,
    likeCount = this.data.like.count
)
