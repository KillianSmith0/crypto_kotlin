package com.example.kismith.cryptoapifetcher.ui

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.style.StyleSpan
import com.bumptech.glide.Glide
import com.example.kismith.cryptoapifetcher.R
import com.example.kismith.cryptoapifetcher.getImageUrl
import com.example.kismith.cryptoapifetcher.model.CryptoResponse
import com.example.kismith.cryptoapifetcher.setDeltaString
import com.example.kismith.cryptoapifetcher.setStyleToSubstring
import kotlinx.android.synthetic.main.currency_window.*


class SingleCurrencyActivity : AppCompatActivity() {

    companion object {
        private val cryptoItemKey = "cryptoCurrency"

        fun start(cryptoItem: CryptoResponse, context: Context) {
            val currencyIntent = Intent(context, SingleCurrencyActivity::class.java)
            currencyIntent.putExtra(cryptoItemKey, cryptoItem)
            context.startActivity(currencyIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.currency_window)
        this.setFinishOnTouchOutside(true)
        val cryptoItem = intent.getSerializableExtra(cryptoItemKey) as CryptoResponse

        val url = getImageUrl(this, cryptoItem.symbol)
        if (url != null) {
            Glide.with(this).load(url).into(imageView)
        } else {
            Glide.with(imageView).clear(imageView)
            imageView.setImageResource(R.drawable.ic_coin_generic)
        }

        val styles = listOf(StyleSpan(Typeface.BOLD))

        coin_title.text = "${cryptoItem.symbol} | ${cryptoItem.name}".setStyleToSubstring(cryptoItem.symbol, styles)
        rank.text = "Rank: #${cryptoItem.rank}".setStyleToSubstring("${cryptoItem.rank}", styles)

        price_currency.text = "Price: €%.2f".format(cryptoItem.price)
        price_btc.text = "To Bitcoin ratio: %.2f".format(cryptoItem.priceBTC)
        market_cap_usd.text = "Market cap: %.2f".format(cryptoItem.marketCapUSD)
        percent_change_1hr.text = "Delta (last hour): ${cryptoItem.hourlyDelta}%".setDeltaString(this,cryptoItem.hourlyDelta)
        percent_change_24h.text = "Delta (last day): ${cryptoItem.dailyDelta}%".setDeltaString(this, cryptoItem.dailyDelta)
        percent_change_7d.text = "Delta (last week): ${cryptoItem.weeklyDelta}%".setDeltaString(this, cryptoItem.weeklyDelta)

        button_yes.setOnClickListener { v ->
            this.finish()
        }
    }
}
