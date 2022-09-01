package com.example.king_bob_nae.features.create.detail.domain

import com.example.king_bob_nae.base.di.DispatcherModule
import com.example.king_bob_nae.features.create.detail.domain.model.KkiLogRecipe
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ConvertDescriptionListUseCase @Inject constructor(
    @DispatcherModule.DispatcherDefault
    private val defaultDispatcher: CoroutineDispatcher
) {
    operator fun invoke(list: List<KkiLogRecipe>): String =
        list.joinToString(SEPARATOR) {
            it.description
        }

    companion object {
        const val SEPARATOR = ","
    }
}
