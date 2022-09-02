package com.example.king_bob_nae.shared

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.king_bob_nae.R
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

fun View.setUnderLineColor() {
    if (this.hasFocus()) {
        this.backgroundTintList = ContextCompat.getColorStateList(context, R.color.main_orange)
    } else {
        this.backgroundTintList = ContextCompat.getColorStateList(context, R.color.brown_gray_200)
    }
}

fun TextView.isEnabled(isEnabled: Boolean) {
    setTextColor(
        ContextCompat.getColor(context, if (isEnabled) R.color.white else R.color.brown_gray_300)
    )
    setBackgroundResource(if (isEnabled) R.drawable.radius_orange else R.drawable.radius_gray)
}
