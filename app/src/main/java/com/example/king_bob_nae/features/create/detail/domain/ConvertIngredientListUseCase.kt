package com.example.king_bob_nae.features.create.detail.domain

import com.example.king_bob_nae.base.di.DispatcherModule
import com.example.king_bob_nae.features.create.detail.domain.model.KkiLogIngredient
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ConvertIngredientListUseCase @Inject constructor(
    @DispatcherModule.DispatcherDefault
    private val defaultDispatcher: CoroutineDispatcher
) {
    operator fun invoke(list: List<KkiLogIngredient>): String =
        list.joinToString(SEPARATOR) {
            it.ingredient
        }

    companion object {
        const val SEPARATOR = ","
    }
}
