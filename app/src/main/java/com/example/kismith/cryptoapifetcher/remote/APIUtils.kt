package com.example.kismith.cryptoapifetcher.remote

/**
 * Created by kismith on 23/03/2018.
 * The API Utilities class
 * Holds the static base URL.
 * Provides the Service interface to the application through the ApiUtils.getCryptService method()
 */
object APIUtils {
    private const val BASE_URL: String = "https://api.coinmarketcap.com/v1/"
    val cryptoService: CryptoService = RetrofitClient.getClient(BASE_URL).create(CryptoService::class.java)
}