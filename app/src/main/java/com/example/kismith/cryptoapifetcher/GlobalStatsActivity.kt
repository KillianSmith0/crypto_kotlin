package com.example.kismith.cryptoapifetcher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.kismith.cryptoapifetcher.model.GlobalStatsResponse
import com.example.kismith.cryptoapifetcher.model.remote.CryptoService
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
        val cryptoService = ApiUtils.cryptoService

        cryptoService.getGlobalStats().enqueue(object: Callback<GlobalStatsResponse>{
            override fun onResponse(call: Call<GlobalStatsResponse>?, response: Response<GlobalStatsResponse>?) {

            }

            override fun onFailure(call: Call<GlobalStatsResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}