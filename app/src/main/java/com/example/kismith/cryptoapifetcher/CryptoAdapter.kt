package com.example.kismith.cryptoapifetcher

import android.graphics.Typeface
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.kismith.cryptoapifetcher.model.CryptoResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.crypto_cell_constraint.view.*
import java.io.InputStreamReader


/**
 * Created by kismith on 23/03/2018.
 */
class CryptoAdapter() : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {
    private var list: List<CryptoResponse> = ArrayList<CryptoResponse>(0)

    fun updateList(items: List<CryptoResponse>?) {
        if (items == null) else list = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.bindCryptoItem(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.crypto_cell_constraint, parent, false)
        return CryptoViewHolder(view)

    }

    class CryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindCryptoItem(cryptoItem: CryptoResponse) {

            val name = SpannableString("${cryptoItem.symbol} | ${cryptoItem.name}")
            name.setSpan(StyleSpan(Typeface.BOLD), 0, cryptoItem.symbol.length, 0)

            val dayDelta = setDeltaString(cryptoItem.dailyDelta, "24hr: ${cryptoItem.dailyDelta}%")
            val weekDelta = setDeltaString(cryptoItem.weeklyDelta, "7d: ${cryptoItem.weeklyDelta}%")
            cryptoItem.symbol
            // get the symbol e.g. BTC
            // get the url from the string res where name == symbol

            val url = getImageUrl(cryptoItem.symbol)
            if (url != null) {
                Glide.with(itemView.context).load(url).into(itemView.coin_iv)
            } else {
                Glide.with(itemView.context).clear(itemView.coin_iv)
                itemView.coin_iv.setImageResource(R.drawable.ic_coin_generic)
            }


            itemView.coin_name.text = name
            itemView.coin_price.text = "${cryptoItem.price} $"
            itemView.coin_day_delta.text = dayDelta
            itemView.coin_week_delta.text = weekDelta

        }

        private fun setDeltaString(delta: Double, text: String): SpannableString {
            val value = delta.toString()
            val spannable = SpannableString(text)

            val color: Int
            val plus = getColor(R.color.plus_cyan)
            val minus = getColor(R.color.minus_red)
            val default = getColor(R.color.text_color)

            color = if (delta > 0) plus else if (delta < 0) minus else default

            if (text.contains(value)) {
                spannable.setSpan(
                        ForegroundColorSpan(color),
                        text.indexOf(value),
                        text.length,
                        0)
                spannable.setSpan(
                        StyleSpan(Typeface.BOLD),
                        text.indexOf(value),
                        text.length,    // til the end of the string
                        0)
            }
//            text.indexOf(value) + value.length iff substring only
            return spannable
        }

        private fun getColor(colorId: Int) = ResourcesCompat.getColor(itemView.resources, colorId, null)

        private fun getImageUrl(symbol: String): String? {
            val gson = Gson()
            val images = itemView.context.assets.open("coin_images.json").reader().readText()
            val typeToken = object : TypeToken<Map<String, String>>() {}.type
            val map = gson.fromJson<Map<String, String>>(images, typeToken)

            return map[symbol]
        }
    }
}