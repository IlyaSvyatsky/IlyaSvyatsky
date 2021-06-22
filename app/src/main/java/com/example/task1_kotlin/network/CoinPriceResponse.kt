package com.example.task1_kotlin.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

public class CoinPriceResponse(

    @SerializedName("data")
    @Expose
    private var coinPrices: Array<Price?>
){
    fun getCoinPrices(): Array<Price?>? {
        return coinPrices
    }

    fun setCoinPrices(coinPrices: Array<Price?>) {
        this.coinPrices = coinPrices
    }
}