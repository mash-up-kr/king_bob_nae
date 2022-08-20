package com.example.king_bob_nae.utils

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.*

fun Uri.toMultipartBody(context: Context, name: String): MultipartBody.Part? =
    if (scheme == "content") {
        toContent(context)?.toMultipartBody(name)
    } else {
        path?.let {
            val file = File(it)
            if (file.exists()) {
                file.toMultipartBody(name)
            } else {
                null
            }
        }
    }

fun ByteArray.toMultipartBody(name: String): MultipartBody.Part =
    MultipartBody.Part.createFormData(
        name = name,
        filename = "image.jpg",
        body = toRequestBody(contentType = MultipartBody.FORM)
    )

fun File.toMultipartBody(name: String): MultipartBody.Part? {
    // When uploading an image file which file name contains Korean, an error occurred on the server.
    // The server does not require file name of the image so we do not send it.
    // TODO 위 코멘트 빙글때와 마찬가지로 같은지 확인 필요
    return MultipartBody.Part.createFormData(
        name = name,
        filename = null,
        body = asRequestBody(MultipartBody.FORM)
    )
}

fun Uri.toContent(context: Context): ByteArray? = context.contentResolver?.let {
    toContentFromFileDescriptor(it) ?: toContentFromInputStream(it)
}

fun Uri.toContentFromFileDescriptor(contentResolver: ContentResolver): ByteArray? {
    val descriptor = try {
        contentResolver.openFileDescriptor(this, "r") ?: return null
    } catch (e: FileNotFoundException) {
        return null
    }

    val fileInputStream = FileInputStream(descriptor.fileDescriptor)
    return try {
        fileInputStream.readBytes()
    } catch (e: IOException) {
        null
    } finally {
        descriptor.close()
        fileInputStream.close()
    }
}

fun Uri.toContentFromInputStream(contentResolver: ContentResolver): ByteArray? {
    val inputStream = try {
        contentResolver.openInputStream(this) ?: return null
    } catch (e: FileNotFoundException) {
        return null
    }

    return try {
        inputStream.readBytes()
    } catch (e: IOException) {
        null
    } finally {
        inputStream.close()
    }
}

fun Bitmap.getByteArray() = ByteArrayOutputStream().apply {
    compress(Bitmap.CompressFormat.JPEG, 100, this)
}.toByteArray()