package com.example.king_bob_nae.utils

import android.util.Log

object NLog {

    @JvmStatic
    fun d(tag: String?, msg: String) {
        Log.d(tag, currentLineInfo + msg)
    }

    private val threadName: String
        get() {
            var threadName = Thread.currentThread().name
            if (threadName.length > 30) {
                threadName = threadName.substring(0, 30) + "..."
            }
            return threadName
        }

    private val currentLineInfo: String
        get() = try {
            val trace = Thread.currentThread().stackTrace[4]
            val strFileName = trace.fileName
            val strMethodName = trace.methodName
            val nLineNumber = trace.lineNumber
            "[$strFileName][$strMethodName:$nLineNumber][$threadName]"
        } catch (e: Exception) {
            ""
        }
}
