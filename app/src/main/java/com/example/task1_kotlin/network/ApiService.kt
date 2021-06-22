package com.example.task1_kotlin.network

import retrofit2.Call
import retrofit2.http.GET

//Интерфейс содержит все методы, которые будут поддерживаться клиентом.
interface ApiService {

    @GET("assets")
    fun getCoins(): Call<CoinResponse?>?

    //GET/assets/{{id}}/history
    @GET("history?interval=d1")
    fun getCoinPrice(): Call<CoinPriceResponse?>?

}