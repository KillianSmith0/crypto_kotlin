package com.example.kismith.cryptoapifetcher

import android.content.Context
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by kismith on 27/03/2018.
 */
fun getImageUrl(context: Context, symbol: String): String? {
    val gson = Gson()
    val images = context.assets.open("coin_images.json").reader().readText()
    val typeToken = object : TypeToken<Map<String, String>>() {}.type
    val map = gson.fromJson<Map<String, String>>(images, typeToken)

    return map[symbol]
}

/**
 * Modifies the substring inside the string that calls the method with the given styles.
 * Iff the string contains the substring*/
fun String.setStyleToSubstring(substring: String, styles: List<Any>): SpannableString {
    val spannable = SpannableString(this)
    if (this.contains(substring)) {
        Log.i("applyStyleToSubstr", "String contains substring")
        for (style in styles) {
            spannable.setSpan(
                    style,
                    this.indexOf(substring),
                    this.length,
                    0)
        }
    }
    return spannable
}

/**
 * Used to modify the text color of the delta strings within a string.
 * */
fun String.setDeltaString(context: Context, delta: Double): SpannableString {
    val value = delta.toString()

    var color: Int
    val plus = R.color.plus_cyan
    val minus = R.color.minus_red
    val default = R.color.text_color

    color = if (delta > 0) plus else if (delta < 0) minus else default
    color = ContextCompat.getColor(context, color)
    val styles = listOf(ForegroundColorSpan(color), StyleSpan(Typeface.BOLD))

    return this.setStyleToSubstring(value, styles)
}