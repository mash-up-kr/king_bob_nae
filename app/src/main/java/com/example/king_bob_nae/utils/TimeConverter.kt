package com.example.king_bob_nae.utils

import java.text.SimpleDateFormat
import java.util.*

class TimeConverter {
    fun getCurrentDate(): String {
        val date = Date(System.currentTimeMillis())
        val dateFormat = SimpleDateFormat("M월 dd일")
        return dateFormat.format(date)
    }
}
