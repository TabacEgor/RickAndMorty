package com.example.rickandmorty.utils

import android.content.res.Resources

object DeviceUtil {
    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}