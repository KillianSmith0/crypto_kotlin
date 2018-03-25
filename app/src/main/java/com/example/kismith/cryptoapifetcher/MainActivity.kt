package com.example.kismith.cryptoapifetcher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.kismith.cryptoapifetcher.model.CryptoResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private val adapter = CryptoAdapter()
    private val cryptoService = ApiUtils.cryptoService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        crypto_rv.adapter = adapter
        crypto_rv.layoutManager = LinearLayoutManager(this)
        crypto_rv.setHasFixedSize(true)
        crypto_rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        loadCurrencies()
//        val cryptoItem = CryptoItem("bitcoin", "Bitcoin", "BTC", "1", "100.00")
//        Log.i("CryptoApp", cryptoItem.getDoubleFrom(cryptoItem.price).toString())
//        Log.i("CryptoApp", cryptoItem.getDoubleFrom(cryptoItem.name).toString())
    }

    private fun loadCurrencies(){
        cryptoService?.getCurrencies()?.enqueue(object : Callback<List<CryptoResponse>> {
            override fun onResponse(call: Call<List<CryptoResponse>>?, response: Response<List<CryptoResponse>>?) {

                if(response?.isSuccessful!!){
                    adapter.updateList(response.body())
                    Log.i("onResponse", response.body().toString())
                    Log.i("Main Activity", "Currencies loaded from API")
                }else {
                    val statusCode = response.code()
                    Log.i("StatusCode", "$statusCode")
                }
            }

            override fun onFailure(call: Call<List<CryptoResponse>>?, t: Throwable?) {

                Log.i("Main Activity", "Currencies failed to load from API")
                Log.i("Main Activity", "${call.toString()}, ${t.toString()}")
            }
        })
    }
    private fun loadGlobalStats() {
        cryptoService?.getGlobalStats("EUR")?.enqueue(object: Callback<CryptoResponse> {
            override fun onFailure(call: Call<CryptoResponse>?, t: Throwable?) {
                Log.i("GlobalStats", "SUCCESS")
            }

            override fun onResponse(call: Call<CryptoResponse>?, response: Response<CryptoResponse>?) {
                Log.i("GlobalStats", "FAILURE")
            }
        })
    }
}
