package com.example.king_bob_nae.utils

import android.text.Editable
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart

fun EditText.afterTextChangedFlow(): Flow<Editable> = callbackFlow {
    val listener = this@afterTextChangedFlow.doOnTextChanged { _, _, _, _ ->
        text?.let { trySend(it) }
    }
    awaitClose { this@afterTextChangedFlow.removeTextChangedListener(listener) }
}.onStart { text?.let { emit(it) } }