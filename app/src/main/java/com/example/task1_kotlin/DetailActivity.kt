package com.example.task1_kotlin

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.task1_kotlin.network.ApiService
import com.example.task1_kotlin.network.Coin
import com.example.task1_kotlin.network.CoinPriceResponse
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Вторая активность должна принять данные от первой активности:
class DetailActivity : AppCompatActivity() {
    private var mTextName: TextView? = null
    private var mTextSymbol: TextView? = null
    private var mTextPriceUSD: TextView? = null
    private var mTextAveragePrice: TextView? = null
    private var mTextPercentChanged: TextView? = null
    private var mTextAvailableSupply: TextView? = null
    private var mTextMaximumSupply: TextView? = null
    private var mTextMarketCapUSD: TextView? = null
    private var mTextValumeUSD24Hr: TextView? = null
    private var mCoinProperty: Coin? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        init()

        //Вторая активность принимает данные от первой активности
        mCoinProperty = intent.getParcelableExtra(MainActivity.COIN_DETAILS_KEY)
        if (mCoinProperty != null) {
            getData()
        }
        getCoinPrice()
    }

    private fun getCoinPrice() {

        var retrofit: Retrofit? = null
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.coincap.io/v2/assets/" + mCoinProperty!!.getId().toString() + "/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //из интрфейса создаем реализацию с методами
        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.getCoinPrice()
        //делаем асинхр запрос
        call!!.enqueue(object: Callback<CoinPriceResponse?> {

            override fun onResponse(
                call: Call<CoinPriceResponse?>,
                response: Response<CoinPriceResponse?>
            ) {
                val graphView = findViewById<View>(R.id.graph) as GraphView
                val series = LineGraphSeries<DataPoint>()

                for (i in response.body()!!.getCoinPrices()!!.indices) {

                    val y = response.body()!!.getCoinPrices()!![i]!!.getPriceUsd()!!.toDouble()

                    series.appendData(DataPoint(i.toDouble(), y.toDouble()), true, response.body()!!.getCoinPrices()!!.size)
                }
                graphView.addSeries(series)
                series.color = Color.rgb(147, 112, 219)
                series.title = "Price Curve 1"
                series.thickness = 5
                graphView.title = "Price Graph"
                graphView.titleTextSize = 50f
                graphView.titleColor = Color.rgb(255, 255, 255)
                graphView.legendRenderer.isVisible = true
                graphView.legendRenderer.align = LegendRenderer.LegendAlign.TOP
                graphView.legendRenderer.textColor = Color.rgb(255, 255, 255)
                val gridLabelRenderer = graphView.gridLabelRenderer
                gridLabelRenderer.horizontalAxisTitle = "X Axis Title"
                gridLabelRenderer.horizontalAxisTitleTextSize = 30f
                gridLabelRenderer.verticalAxisTitle = "Y Axis Title"
                gridLabelRenderer.verticalAxisTitleTextSize = 30f
                gridLabelRenderer.gridColor = Color.rgb(255, 255, 255)
                gridLabelRenderer.horizontalAxisTitleColor = Color.rgb(
                    147,
                    112,
                    219
                )
                gridLabelRenderer.verticalAxisTitleColor = Color.rgb(
                    147,
                    112,
                    219
                )
                gridLabelRenderer.verticalLabelsColor = Color.rgb(255, 255, 255)
                gridLabelRenderer.horizontalLabelsColor = Color.rgb(
                    255,
                    255,
                    255
                )
            }

            override fun onFailure(call: Call<CoinPriceResponse?>, t: Throwable) {
                Toast.makeText(this@DetailActivity, "Unable to proceed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun init() {
        mTextName = findViewById(R.id.text_name)
        mTextSymbol = findViewById(R.id.text_symbol)
        mTextPriceUSD = findViewById(R.id.coin_price)
        mTextAveragePrice = findViewById(R.id.text_average_price)
        mTextPercentChanged = findViewById(R.id.coin_percent_change)
        mTextAvailableSupply = findViewById(R.id.text_available_supply)
        mTextMaximumSupply = findViewById(R.id.text_max_supply)
        mTextMarketCapUSD = findViewById(R.id.text_market_cap)
        mTextValumeUSD24Hr = findViewById(R.id.text_volume)
    }

    private fun getData() {
        mTextName!!.text = mCoinProperty!!.getId()
        mTextSymbol!!.text = mCoinProperty!!.getSymbol()
        mTextAveragePrice!!.text = getString(
            R.string.text_average_price,
            mCoinProperty!!.getVwap24Hr()!!.toDouble()
        )
        mTextAvailableSupply!!.text = getString(
            R.string.text_available_supply,
            mCoinProperty!!.getSupply()!!.toDouble()
        )
        mTextMaximumSupply!!.text = getString(
            R.string.text_average_price,
            mCoinProperty!!.getMaxSupply()!!.toDouble()
        )
        mTextMarketCapUSD!!.text = getString(
            R.string.text_market_cap,
            mCoinProperty!!.getMarketCapUsd()!!.toDouble()
        )
        mTextValumeUSD24Hr!!.text = getString(
            R.string.text_volume,
            mCoinProperty!!.getVolumeUsd24Hr()!!.toDouble()
        )
        mTextPriceUSD!!.text = getString(
            R.string.coin_price,
            mCoinProperty!!.getPriceUsd()!!.toDouble()
        )
        mTextPercentChanged!!.text = getString(
            R.string.coin_percent_change,
            mCoinProperty!!.getChangePercent24Hr()!!.toDouble()
        )
        if (mCoinProperty!!.getChangePercent24Hr()!!.toDouble() >= 0) {
            mTextPercentChanged!!.setTextColor(resources.getColor(R.color.percentChangePositive))
        } else {
            mTextPercentChanged!!.setTextColor(resources.getColor(R.color.percentChangeNegative))
        }
    }
}

