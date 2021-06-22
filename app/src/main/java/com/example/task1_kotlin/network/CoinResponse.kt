package com.example.task1_kotlin.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

public class CoinResponse(

    @SerializedName("data")
    @Expose
    private var coins: List<Coin>
){
    fun getCoins(): List<Coin> {
        return coins
    }

    fun setCoins(coins: List<Coin>) {
        this.coins = coins
    }
}