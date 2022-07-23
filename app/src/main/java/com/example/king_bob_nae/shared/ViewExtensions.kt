package com.example.king_bob_nae.shared

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.king_bob_nae.utils.ThrottleClickListener

@BindingAdapter("onThrottleClick")
fun View.setOnThrottleClickListener(listener: View.OnClickListener) {
    setOnClickListener(
        ThrottleClickListener {
            listener.onClick(it)
        }
    )
}

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}
