package com.example.king_bob_nae.features.create.kkilog.data.dto

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

data class UpLoadDto(
    val images: List<String>,
    val title: String,
    val description: String?,
    val kick: String?
)

fun String.stringToMultiPartBody(name: String): MultipartBody.Part {
    return MultipartBody.Part.createFormData(name, this)
}

fun String.stringToNullableRequestBody(): RequestBody? =
    this.toRequestBody("text/plain".toMediaTypeOrNull())

fun List<String>.listToMultiPartBody(): List<MultipartBody.Part> {
    val multipartList = mutableListOf<MultipartBody.Part>()
    this.map {
        val file = File(it)

        val requestFile = file.asRequestBody("image/${file.extension}".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("images", file.name, requestFile)
        multipartList.add(body)
    }
    return multipartList
}

val File.extension: String
    get() = name.substringAfterLast('.', "")