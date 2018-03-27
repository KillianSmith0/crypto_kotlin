package com.example.kismith.cryptoapifetcher.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.kismith.cryptoapifetcher.ApiUtils
import com.example.kismith.cryptoapifetcher.R
import com.example.kismith.cryptoapifetcher.model.GlobalStatsResponse
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

        val cryptoService = ApiUtils.cryptoService

        cryptoService.getGlobalStats().enqueue(object : Callback<GlobalStatsResponse> {

            override fun onResponse(call: Call<GlobalStatsResponse>, response: Response<GlobalStatsResponse>) {
                Log.i("globalStats", response.body().toString())
                if (response.isSuccessful) {
                    val global = response.body()
                    if (global != null) {
                        total_market_cap.text = "€${global.totalMarketCap}"
                        total_day_volume.text = "€${global.totalDayVolume}"
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

//    class statsAdapter(context: Context) : Adapter {
//        var data = ArrayList<GlobalStatsResponse>(0)
//        override fun getViewTypeCount(): Int {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        }
//
//        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//            val model = getItem(position)
//
//        }
//
//        override fun getItemViewType(position: Int): Int {
//        }
//
//        override fun getItem(position: Int): GlobalStatsResponse = data[position]
//
//        override fun getItemId(position: Int): Long {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        }
//
//
//
//        private class statsViewHolder() {
//            val label: TextView
//            val value: TextView
//        }
//    }
}