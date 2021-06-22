package com.example.task1_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task1_kotlin.adapter.CoinAdapter
import com.example.task1_kotlin.adapter.OnCoinClickListener
import com.example.task1_kotlin.network.ApiService
import com.example.task1_kotlin.network.Coin
import com.example.task1_kotlin.network.CoinResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), OnCoinClickListener {

    var recyclerView: RecyclerView? = null
    var mProgressBar: ProgressBar? = null

    val adapter = CoinAdapter()

    var mCoinList: List<Coin>? = null

    val COIN_DETAILS_KEY = "com.example.task1_kotlin.COIN_DETAILS_KEY"

    val BASE_URL = "https://api.coincap.io/v2/"


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        mProgressBar = findViewById(R.id.progress_bar)

        //adapter.setCoins(mCoinList)
        adapter.setOnCoinClickListener(this)

        //В созданном экземпляре RecyclerView мы должны установить LayoutManager - как отбражать данные.
        recyclerView!!.setLayoutManager(LinearLayoutManager(this))
        recyclerView!!.setAdapter(adapter)

        getCoins()
    }

    private fun getCoins() {

        var retrofit: Retrofit? = null
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        //из интрфейса создаем реализацию с методами
        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.getCoins()
        //делаем асинхр запрос
        call!!.enqueue(object : Callback<CoinResponse?> {
            override fun onResponse(
                call: Call<CoinResponse?>,
                response: Response<CoinResponse?>
            ) {
                if (response.isSuccessful) {
                    mProgressBar!!.visibility = View.GONE
                    var coinResponse = response.body()

                    //mCoinList =  ArrayList<Coin?>(Arrays.asList(coinResponse?.getCoins()))
                    //mCoinList =  ArrayList<Coin?>(Arrays.asList(coinResponse?.getCoins()))
                    adapter.setCoins(response.body()?.getCoins())
                    recyclerView!!.adapter = adapter
                }
            }

            override fun onFailure(
                call: Call<CoinResponse?>,
                t: Throwable
            ) {
                mProgressBar!!.visibility = View.GONE
                Toast.makeText(this@MainActivity, "Unable to proceed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    //Теперь мы может передать объект через Intent.
    // Создадим две активности. В первой активности напишем код для отправки объекта
    override fun onCoinClick(coinProperty: Coin?) {
        var intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(COIN_DETAILS_KEY, coinProperty)
        startActivity(intent)
    }

    companion object {
        const val COIN_DETAILS_KEY = "com.example.task1_kotlin.COIN_DETAILS_KEY"
    }
}