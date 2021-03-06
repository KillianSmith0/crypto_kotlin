package com.example.kismith.cryptoapifetcher.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.kismith.cryptoapifetcher.R
import com.example.kismith.cryptoapifetcher.model.GlobalStatsResponse
import com.example.kismith.cryptoapifetcher.remote.APIUtils
import kotlinx.android.synthetic.main.global_stats_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by kismith on 26/03/2018.
 */
class GlobalStatsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.global_stats_view)

        setSupportActionBar(global_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val cryptoService = APIUtils.cryptoService

        cryptoService.getGlobalStats().enqueue(object : Callback<GlobalStatsResponse> {

            override fun onResponse(call: Call<GlobalStatsResponse>, response: Response<GlobalStatsResponse>) {
                Log.i("globalStats", response.body().toString())
                if (response.isSuccessful) {
                    val global = response.body()
                    if (global != null) {
                        total_market_cap.text = getString(R.string.price, global.totalMarketCap)
                        total_day_volume.text = getString(R.string.price, global.totalDayVolume)
                        percentage_of_market_cap.text = "${global.bitcoinMarketCap}%"
                        active_currencies.text = global.activeCurrencies.toString()
                        active_assets.text = global.activeAssets.toString()
                        active_markets.text = global.activeMarkets.toString()
                    }
                }
            }

            override fun onFailure(call: Call<GlobalStatsResponse>, t: Throwable) {
                Log.i("globalStats", t.toString())
            }
        })
    }

}