package com.example.kismith.cryptoapifetcher

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.kismith.cryptoapifetcher.model.CryptoResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.crypto_cell_constraint.view.*


fun getImageUrl(context: Context, symbol: String): String? {
    val gson = Gson()
    val images = context.assets.open("coin_images.json").reader().readText()
    val typeToken = object : TypeToken<Map<String, String>>() {}.type
    val map = gson.fromJson<Map<String, String>>(images, typeToken)

    return map[symbol]
}

class CryptoAdapter() : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {
    private var list: List<CryptoResponse> = ArrayList<CryptoResponse>(0)

    fun updateList(items: List<CryptoResponse>?) {
        list = items ?: list
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

            val url = getImageUrl(itemView.context, cryptoItem.symbol)
            if (url != null) {
                Glide.with(itemView.context).load(url).into(itemView.coin_iv)
            } else {
                Glide.with(itemView.context).clear(itemView.coin_iv)
                itemView.coin_iv.setImageResource(R.drawable.ic_coin_generic)
            }


            itemView.coin_name.text = name
            itemView.coin_price.text = "€${cryptoItem.price}"
            itemView.coin_day_delta.text = dayDelta
            itemView.coin_week_delta.text = weekDelta

            itemView.setOnClickListener { v ->
                // Create a dialog
                SingleCurrencyActivity.start(cryptoItem, itemView.context)

            }

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

    }
}