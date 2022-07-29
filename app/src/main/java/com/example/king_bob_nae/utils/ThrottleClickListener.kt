package com.example.king_bob_nae.utils

import android.view.View

class ThrottleClickListener(private val listener: (View) -> Unit) : View.OnClickListener {
    private var lastTime = 0L

    override fun onClick(v: View?) {
        val now = System.currentTimeMillis()
        if (now - lastTime < THROTTLE_CLICK_INTERVAL) {
            return
        }
        lastTime = now

        v?.let {
            listener(it)
        }
    }

    companion object {
        private const val THROTTLE_CLICK_INTERVAL = 500L
    }
}
