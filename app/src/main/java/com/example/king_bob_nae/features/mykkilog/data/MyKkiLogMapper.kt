package com.example.king_bob_nae.features.mykkilog.data

data class MyKkiLogThumbNail(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val date: String,
    val isSimple: Boolean
)

fun List<MyKkiLogResponse>.toKkiLogList() = this.map {
    MyKkiLogThumbNail(
        id = it.id,
        imageUrl = it.image.w56,
        title = it.title,
        date = it.createdAt,
        isSimple = true
    )
}

fun List<MyKkiLogResponse>.toDetailList() = this.map {
    MyKkiLogThumbNail(
        id = it.id,
        imageUrl = it.image.w56,
        title = it.title,
        date = it.createdAt,
        isSimple = false
    )
}

