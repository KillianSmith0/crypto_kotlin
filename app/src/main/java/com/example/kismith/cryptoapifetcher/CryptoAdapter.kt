package com.example.kismith.cryptoapifetcher

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by kismith on 23/03/2018.
 */
class CryptoAdapter() : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

    fun fetchCurrency(){
        TODO("not implemented")
    }

    override fun getItemCount(): Int {
        TODO("not implemented")
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        TODO("not implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        TODO("not implemented")
    }


    class CryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bindCryptoItem(cryptoItem: CryptoItem){

        }
    }
}