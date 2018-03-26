package com.example.kismith.cryptoapifetcher.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by kismith on 23/03/2018.
 *
 */
object RetrofitClient {

    private lateinit var retrofit: Retrofit

    fun getClient(baseUrl: String): Retrofit {
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit
    }
}