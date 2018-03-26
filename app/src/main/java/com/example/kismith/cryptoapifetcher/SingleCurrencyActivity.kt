package com.example.kismith.cryptoapifetcher

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.kismith.cryptoapifetcher.model.CryptoResponse
import kotlinx.android.synthetic.main.currency_window.*


class SingleCurrencyActivity : AppCompatActivity() {

    companion object {
        private val cryptoItemKey = "cryptoCurrency"

        fun start(cryptoItem: CryptoResponse, context: Context){
            val currencyIntent = Intent(context, SingleCurrencyActivity::class.java)
            currencyIntent.putExtra(cryptoItemKey, cryptoItem)
            context.startActivity(currencyIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.currency_window)

        val cryptoItem = intent.getSerializableExtra(cryptoItemKey) as CryptoResponse

        val url = getImageUrl(this, cryptoItem.symbol)
        if (url != null) {
            Glide.with(this).load(url).into(imageView)
        } else {
            Glide.with(imageView).clear(imageView)
            imageView.setImageResource(R.drawable.ic_coin_generic)
        }

        coin_title.text = "${cryptoItem.symbol} | ${cryptoItem.name}"
        rank.text = "Rank: #${cryptoItem.rank}"
        price_currency.text = "Price: €%.2f".format(cryptoItem.price)
        price_btc.text = "To Bitcoin ratio: %.2f".format(cryptoItem.priceBTC)
        market_cap_usd.text = "Market cap: %.2f".format(cryptoItem.marketCapUSD)
        percent_change_1hr.text = "Delta (last hour): %.2f".format(cryptoItem.hourlyDelta)
        percent_change_24h.text = "Delta (last day): %.2f".format(cryptoItem.dailyDelta)
        percent_change_7d.text = "Delta (last week): %.2f".format(cryptoItem.weeklyDelta)

        button_yes.setOnClickListener { v ->
            val list = Intent(this, MainActivity::class.java)
            startActivity(list)
        }
    }


}
