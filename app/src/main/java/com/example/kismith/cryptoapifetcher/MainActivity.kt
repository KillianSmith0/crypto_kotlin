package com.example.kismith.cryptoapifetcher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cryptoList: RecyclerView = crypto_rv
        cryptoList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))

//        val cryptoItem = CryptoItem("bitcoin", "Bitcoin", "BTC", "1", "100.00")
//        Log.i("CryptoApp", cryptoItem.getDoubleFrom(cryptoItem.price).toString())
//        Log.i("CryptoApp", cryptoItem.getDoubleFrom(cryptoItem.name).toString())


    }
}
