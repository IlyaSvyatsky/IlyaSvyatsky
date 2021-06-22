package com.example.task1_kotlin.adapter

import com.example.task1_kotlin.network.Coin


public interface OnCoinClickListener {

    public fun onCoinClick(coin: Coin?)
}