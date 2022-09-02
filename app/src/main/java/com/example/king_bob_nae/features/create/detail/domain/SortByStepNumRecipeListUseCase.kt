package com.example.king_bob_nae.features.create.detail.domain

import com.example.king_bob_nae.base.di.DispatcherModule
import com.example.king_bob_nae.features.create.detail.domain.model.KkiLogRecipe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SortByStepNumRecipeListUseCase @Inject constructor(
    @DispatcherModule.DispatcherDefault
    private val defaultDispatcher: CoroutineDispatcher
) {
    operator fun invoke(list: List<KkiLogRecipe>) = flow {
        emit(
            list.sortedBy {
                it.stepNumber
            }
        )
    }.flowOn(defaultDispatcher)
}
