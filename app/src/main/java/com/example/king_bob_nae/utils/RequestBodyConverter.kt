package com.example.king_bob_nae.utils

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.MultipartBody.Part.Companion.createFormData
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

fun String.stringToRequestBody(): RequestBody =
    this.toRequestBody("text/plain".toMediaTypeOrNull())

fun List<String>.listToRequestBody(name: String): List<MultipartBody.Part>? {
    val multipartList = mutableListOf<MultipartBody.Part>()
    this.map {
        multipartList.add(createFormData(name, it))
    }
    return multipartList
}
