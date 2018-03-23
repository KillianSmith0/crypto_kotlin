package com.example.kismith.cryptoapifetcher.model

import android.util.Log
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by kismith on 23/03/2018.
 */
data class CryptoItem(@SerializedName("id") val id: String? = "",
                      val name: String? = "",
                      val symbol: String?  = "",
                      val rank: String? = "0",
                      val price: String? = "0.0",
                      @Expose@SerializedName("price_btc") val priceBTC: String? = "0.0",
                      @SerializedName("24h_volume_usd") val dayVolumeUSD: String? = "0.0",
                      @SerializedName("market_cap_usd") val marketCapUSD: String? = "0.0",
                      @SerializedName("percent_change_1h") val hourlyDelta: String? = "0.0",
                      @SerializedName("percent_change_24h") val dailyDelta: String? = "0.0",
                      @SerializedName("percent_change_7d") val weeklyDelta: String? = "0.0",
                      @SerializedName("last_updated") val lastUpdated: String? = "")


fun CryptoItem.getDoubleFrom(string: String?): Double? {
    try {
        return string?.toDouble()
    } catch (e: NumberFormatException) {
        Log.i("CryptoItem", e.localizedMessage)
        return null
    }
}