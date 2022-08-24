package com.example.king_bob_nae.features.create.detail.domain

import android.net.Uri
import com.example.king_bob_nae.base.di.DispatcherModule
import com.example.king_bob_nae.features.create.detail.domain.model.KkiLogRecipe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ConvertImageListUseCase @Inject constructor(
    @DispatcherModule.DispatcherDefault
    private val defaultDispatcher: CoroutineDispatcher
) {
    operator fun invoke(list: List<KkiLogRecipe>): Flow<List<Uri?>> = flow {
        emit(
            list.map {
                it.imageUri
            }
        )
    }.flowOn(defaultDispatcher)
}
