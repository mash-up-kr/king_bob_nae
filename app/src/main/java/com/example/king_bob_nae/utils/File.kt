package com.example.king_bob_nae.utils

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.example.king_bob_nae.features.imagepicker.presentation.ImageState
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.*

fun String.toMultipartBody(name: String): MultipartBody.Part? {
    val file = File(this)
    return if (file.exists()) {
        file.toMultipartBody(name)
    } else {
        null
    }
}

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

// img_20220830_~ 와 같이 고유 이름만 멀티파트 이름으로 지정
fun MutableList<ImageState>.toMultipartBody(): List<MultipartBody.Part> = this.map {
    it.imageUrl.toMultipartBody(it.imageUrl)
        ?: throw IllegalStateException("fail to convert multipart body")
}

// 이미지 피커 fragment에서 MutableList<ImageState>
// 안에 string으로 사진 절대 경로값이 있는데, toUri()하고 멀티파트로 변환해줌