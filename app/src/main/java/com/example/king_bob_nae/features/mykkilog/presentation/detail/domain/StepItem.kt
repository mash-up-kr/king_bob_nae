package com.example.king_bob_nae.features.mykkilog.presentation.detail.domain

import com.example.king_bob_nae.features.create.detail.data.Recipe

data class StepItem(
    val image: String,
    val step: Int,
    val description: String,
)

fun List<Recipe>.toStep(): List<StepItem> {
    val list = mutableListOf<StepItem>()
    this.map {
        list.add(
            StepItem(
                image = it.image.w1024,
                description = it.description,
                step = this.indexOf(it) + 1
            )
        )
    }
    return list
}