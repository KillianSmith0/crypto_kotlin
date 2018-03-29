package com.example.kismith.cryptoapifetcher

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.kismith.cryptoapifetcher.model.CryptoResponse
import com.example.kismith.cryptoapifetcher.ui.SingleCurrencyActivity
import kotlinx.android.synthetic.main.crypto_cell_constraint.view.*


class CryptoAdapter : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {
    private var list: List<CryptoResponse> = ArrayList(0)

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
            val ctx = itemView.context
            val symbol = cryptoItem.symbol

            val name = "$symbol | ${cryptoItem.name}".setStyleToSubstring(symbol, listOf(StyleSpan(Typeface.BOLD)))
            val dayDelta = setDeltaString(ctx, R.string.day_delta, cryptoItem.dailyDelta)
            val weekDelta = setDeltaString(ctx, R.string.week_delta, cryptoItem.weeklyDelta)

            val url = getImageUrl(ctx, symbol)
            if (url != null) {
                Glide.with(ctx).load(url).into(itemView.coin_iv)
            } else {
                Glide.with(ctx).clear(itemView.coin_iv)
                itemView.coin_iv.setImageResource(R.drawable.ic_coin_generic)
            }

            itemView.coin_name.text = name
            itemView.coin_price.text = ctx.getString(R.string.price_eur, cryptoItem.price)
            itemView.coin_day_delta.text = dayDelta
            itemView.coin_week_delta.text = weekDelta

            itemView.setOnClickListener { _ ->
                SingleCurrencyActivity.start(cryptoItem, ctx)
            }
        }
    }
}