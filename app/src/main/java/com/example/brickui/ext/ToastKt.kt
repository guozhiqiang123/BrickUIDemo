package com.example.brickui.ext

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.seewo.brick.ktx.string

fun Context.toast(charSequence: CharSequence) {
    Toast.makeText(this, charSequence, Toast.LENGTH_SHORT).show()
}

fun Context.toast(@StringRes res: Int) {
    toast(res.string)
}