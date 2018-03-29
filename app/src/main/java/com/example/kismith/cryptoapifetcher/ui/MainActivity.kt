package com.example.kismith.cryptoapifetcher.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.kismith.cryptoapifetcher.CryptoAdapter
import com.example.kismith.cryptoapifetcher.R
import com.example.kismith.cryptoapifetcher.model.CryptoResponse
import com.example.kismith.cryptoapifetcher.remote.APIUtils
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import zendesk.support.guide.HelpCenterActivity


class MainActivity : AppCompatActivity() {
    private val adapter = CryptoAdapter()
    private val cryptoService = APIUtils.cryptoService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(crypto_toolbar)

        Log.i("Main","Main created")
        crypto_rv.adapter = adapter
        crypto_rv.layoutManager = LinearLayoutManager(this)
        crypto_rv.setHasFixedSize(true)
        crypto_rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        loadCurrencies()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.global_action -> {
            val globalIntent = Intent(this, GlobalStatsActivity::class.java)
            startActivity(globalIntent)
            true
        }

        R.id.zendesk_action -> {
            Log.i("Option", "Zendesk Clicked")
            HelpCenterActivity.builder()
                    .show(this)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }

    }

    private fun loadCurrencies() {

        cryptoService.getCurrencies().enqueue(object : Callback<List<CryptoResponse>> {
            override fun onResponse(call: Call<List<CryptoResponse>>, response: Response<List<CryptoResponse>>) {
                if (response.isSuccessful) {
                    adapter.updateList(response.body())
                    Log.i("onResponse", response.body().toString())
                } else {
                    Log.i("StatusCode", "${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<CryptoResponse>>, t: Throwable?) {
                Log.i("Main Activity", "${call}, ${t.toString()}")
            }
        })
    }
}
