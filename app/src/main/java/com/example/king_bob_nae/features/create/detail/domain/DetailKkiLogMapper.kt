package com.example.king_bob_nae.features.create.detail.domain

import com.example.king_bob_nae.features.create.detail.data.DetailKkiLog
import com.example.king_bob_nae.features.create.detail.domain.model.DetailKkiLogResult

fun DetailKkiLog.toDetailKkiLogResult(): DetailKkiLogResult {
    return DetailKkiLogResult(
        createdAt = this.createdAt,
        description = this.description,
        id = this.id,
        image = this.image,
        ingredients = this.ingredients,
        isScrapped = this.isScrapped,
        like = this.like,
        recipes = this.recipes,
        title = this.title,
        updatedAt = this.updatedAt
    )
}
