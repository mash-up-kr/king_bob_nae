package com.example.king_bob_nae.features.mykkilog.presentation.detail.domain

import com.example.king_bob_nae.features.create.detail.data.DetailKkiLogDto

data class StepItem(
    val image: String,
    val step: Int,
    val description: String,
)

fun DetailKkiLogDto.toStep(): List<StepItem> {
    val list = mutableListOf<StepItem>()
    this.data.recipes.map {
        list.add(
            StepItem(
                image = it.image.w1024,
                description = it.description,
                step = this.data.recipes.indexOf(it) + 1
            )
        )
    }
    return list
}
