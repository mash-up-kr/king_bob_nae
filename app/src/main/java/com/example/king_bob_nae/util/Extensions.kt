package com.example.king_bob_nae.util

import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.example.king_bob_nae.R
import com.example.king_bob_nae.util.Extensions.Companion.CERTIFICATION_ERROR
import com.example.king_bob_nae.util.Extensions.Companion.EMAIL_ERROR
import com.example.king_bob_nae.util.Extensions.Companion.NICK_ERROR
import com.example.king_bob_nae.util.Extensions.Companion.PASSWD_ERROR
import com.example.king_bob_nae.util.Extensions.Companion.SIGN_IN_ERROR
import com.google.android.material.textfield.TextInputLayout

class Extensions {
    companion object {
        const val SIGN_IN_ERROR = 0
        const val EMAIL_ERROR = 1
        const val PASSWD_ERROR = 2
        const val NICK_ERROR = 3
        const val CERTIFICATION_ERROR = 4
    }
}

fun TextInputLayout.hideIcon() {
    isEndIconVisible = false
}

fun TextInputLayout.isValid(isValid: (String) -> Boolean) {

    editText?.addTextChangedListener { Editable ->
        error = null
        isEndIconVisible = isValid(Editable.toString())
        setEndIconDrawable(R.drawable.ic_correct_20)
    }
}

fun TextInputLayout.setButtonEnable(textInputLayout: TextInputLayout, button: AppCompatButton) {
    editText?.addTextChangedListener {
        button.isEnabled(isEndIconVisible && textInputLayout.isEndIconVisible)
    }
}

fun TextInputLayout.setButtonEnable(isValid: (String) -> Boolean, button: AppCompatButton) {
    editText?.addTextChangedListener {
        button.isEnabled(isValid(it.toString()))
    }
}

fun TextInputLayout.setError(errorType: Int) {
    when (errorType) {
        SIGN_IN_ERROR -> {
            error = context.getString(R.string.sign_up_input_nick_error)
        }
        EMAIL_ERROR -> {
            error = context.getString(R.string.input_email_error)
        }
        PASSWD_ERROR -> {
            error = context.getString(R.string.sign_up_input_passwd_error)
        }
        NICK_ERROR -> {
            error = context.getString(R.string.sign_up_input_nick_error)
        }
        CERTIFICATION_ERROR -> {
            error = context.getString(R.string.certification_error)
        }
    }
    setEndIconDrawable(R.drawable.ic_error_20)
}

fun AppCompatButton.isEnabled(condition: Boolean) {
    if (condition) {
        isEnabled = condition
        setBackgroundResource(R.drawable.radius_orange)
        setTextColor(
            ContextCompat.getColor(context, R.color.white)
        )
    } else {
        isEnabled = condition
        setBackgroundResource(R.drawable.radius_gray)
        setTextColor(
            ContextCompat.getColor(context, R.color.brown_gray_300)
        )
    }

}