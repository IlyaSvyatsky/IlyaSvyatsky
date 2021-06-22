package com.example.task1_kotlin.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

public class Client {

    val BASE_URL = "https://api.coincap.io/v2/"

    var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}