package com.example.kismith.cryptoapifetcher.remote

import com.example.kismith.cryptoapifetcher.model.CryptoResponse
import com.example.kismith.cryptoapifetcher.model.GlobalStatsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by kismith on 23/03/2018.
 * The API Interface.
 * This interface contains the methods used to execute HTTP requests like GET, POST, PUT, PATCH, etc.
 */
interface CryptoService {

    /** Parameters of the interface methods can have the following annotations:
     * @Path - variable substitution for the API endpoint
     * @Query - specifies the query key name with the value of the annotated param
     * @Body - payload for the POST call
     * @Header - specify the header with the value of the annotated param
     * */


    @GET("ticker/")
    fun getCurrencies(@Query("start") start: Int = 0,
                   @Query("limit") limit: Int = 100,
                   @Query("convert") convert: String = "EUR")
            : Call<List<CryptoResponse>>

    /**
     * Not needed as all currencies are retrieved on app loaded, so take specific currency from list
     * */
//    @GET("ticker/{id}/")
//    fun getSpecificCurrency(@Path("id") id: String, @Query("convert") convert: String)
//            :Call<CryptoResponse>

    @GET("global/")
    fun getGlobalStats(@Query("convert") convert: String = "EUR") : Call<GlobalStatsResponse>

}

enum class Currencies {

}