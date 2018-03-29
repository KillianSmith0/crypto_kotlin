package com.example.kismith.cryptoapifetcher

import android.content.Context
import android.graphics.Typeface
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.text.SpannableString
import android.text.style.CharacterStyle
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by kismith on 27/03/2018.
 */
fun getImageUrl(context: Context, symbol: String): String? {
    val gson = Gson()
    val images = context.assets.open("coin_images.json").reader().readText()
    val typeToken = object : TypeToken<Map<String, String>>() {}.type
    val map = gson.fromJson<Map<String, String>>(images, typeToken) // gson struggles with maps

    return map[symbol]
}

/**
 * Modifies the substring inside the string that calls the method with the given styles.
 * Iff the string contains the substring
 * */
fun String.setStyleToSubstring(substring: String, styles: List<CharacterStyle>): SpannableString {
    val spannable = SpannableString(this)
    if (this.contains(substring)) {

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
fun setDeltaString(context: Context, string: String, delta: Double): SpannableString {
    val value = delta.toString()

    var color: Int
    val plus = R.color.plus_cyan
    val minus = R.color.minus_red
    val default = R.color.text_color

    color = if (delta > 0) plus else if (delta < 0) minus else default
    color = ContextCompat.getColor(context, color)
    val styles = listOf<CharacterStyle>(ForegroundColorSpan(color), StyleSpan(Typeface.BOLD))

    return string.setStyleToSubstring(value, styles)
}

// Overloads the above function to clean up the code where it will actually be called
// Changes to the above method won't effect where it is being called.
fun setDeltaString(context: Context, @StringRes sourceString: Int, delta: Double): SpannableString {
    return setDeltaString(context, context.getString(sourceString, delta), delta)
}