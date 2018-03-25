package com.example.kismith.cryptoapifetcher

import com.example.kismith.cryptoapifetcher.model.remote.CryptoService
import com.example.kismith.cryptoapifetcher.model.remote.RetrofitClient


/**
 * Created by kismith on 23/03/2018.
 * The API Utilities class
 * Holds the static base URL.
 * Provides the Service interface to the application through the ApiUtils.getCryptService method()
 */
object ApiUtils {

    public val BASE_URL: String = "https://api.coinmarketcap.com/v1/"

    public val cryptoService = RetrofitClient.getClient(BASE_URL)?.create(CryptoService::class.java)

}