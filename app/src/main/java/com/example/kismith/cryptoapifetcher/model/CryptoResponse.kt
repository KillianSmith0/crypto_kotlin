package com.example.kismith.cryptoapifetcher.model

import android.util.Log
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by kismith on 23/03/2018.
 */
data class CryptoResponse(@SerializedName("id") val id: String? = "",
                     val name: String = "",
                     val symbol: String = "",
                     val rank: Int = 0,
                     @SerializedName("price_usd") val price: Double,
                     @SerializedName("price_btc") val priceBTC: Double,
                     @SerializedName("24h_volume_usd") val dayVolumeUSD: Double = 0.0,
                     @SerializedName("market_cap_usd") val marketCapUSD: Double = 0.0,
                     @SerializedName("available_supply") val availableSupply:Double = 0.0,
                     @SerializedName("total_supply") val totalSupply:Double = 0.0,
                     @SerializedName("percent_change_1h") val hourlyDelta: Double = 0.0,
                     @SerializedName("percent_change_24h") val dailyDelta: Double = 0.0,
                     @SerializedName("percent_change_7d") val weeklyDelta: Double = 0.0,
                     @SerializedName("last_updated") val lastUpdated: String? = "") : Serializable

data class GlobalStatsResponse(@SerializedName("total_market_cap_usd") val totalMarketCap: Float,
                               @SerializedName("total_24h_volume_usd") val totalDayVolume: Float,
                               @SerializedName("bitcoin_percentage_of_market_cap") val bitcoinMarketCap: Float,
                               @SerializedName("active_currencies") val activeCurrencies: Int,
                               @SerializedName("active_assets") val activeAssets: Int,
                               @SerializedName("active_markets") val activeMarkets: Int,
                               @SerializedName("last_updated") val lastUpdated: Long )

