package com.example.kismith.cryptoapifetcher.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor


/**
 * Created by kismith on 23/03/2018.
 *
 */
object RetrofitClient {

    private lateinit var retrofit: Retrofit

    private val okHTTP: OkHttpClient by lazy {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        OkHttpClient.Builder().addInterceptor(logging).build()
    }

    fun getClient(baseUrl: String): Retrofit {
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHTTP)
                .build()
        return retrofit
    }
}