package com.example.king_bob_nae.features.mykkilog.data

import java.text.SimpleDateFormat
import java.util.*

data class MyKkiLogThumbNail(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val date: String,
    val isSimple: Boolean
)

fun MyKkiLogResponse.toKkiLogList() = this.data?.map {
    MyKkiLogThumbNail(
        id = it.id,
        imageUrl = it.image.w256,
        title = it.title,
        date = timeMapper(it.createdAt),
        isSimple = true
    )
}

fun MyKkiLogResponse.toDetailList() = this.data?.map {
    MyKkiLogThumbNail(
        id = it.id,
        imageUrl = it.image.w256,
        title = it.title,
        date = timeMapper(it.createdAt),
        isSimple = false
    )
}

fun timeMapper(time: String): String {
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.KOREA).parse(time)
    return SimpleDateFormat("yyyy.MM.dd").format(date)
}
