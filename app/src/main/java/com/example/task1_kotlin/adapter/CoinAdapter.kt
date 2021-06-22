package com.example.task1_kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task1_kotlin.R
import com.example.task1_kotlin.network.Coin

class CoinAdapter : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>(){
    private var listener: OnCoinClickListener? = null
    private var coinList: List<Coin>? = null
    private var context: Context? = null

    //создает новый объект CoinViewHolder всякий раз, когда RecyclerView нуждается в этом.
    // Это тот момент, когда создаётся layout строки списка, передается объекту CoinViewHolder,
    // и каждый дочерний view-компонент может быть найден и сохранен.
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): CoinViewHolder {
        val view: View
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        view = layoutInflater.inflate(R.layout.item_coin, viewGroup, false)

        //View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_coins,viewGroup,false);
        return CoinViewHolder(view)
    }

    override fun onBindViewHolder(
        coinViewHolder: CoinViewHolder,
        i: Int
    ) {
        val coin: Coin = coinList!![i]

        coinViewHolder.textViewId.text = coin.getRank()
        coinViewHolder.textViewName.text = coin.getName()
        coinViewHolder.textViewSymbol.text = coin.getSymbol()
        coinViewHolder.textViewPrice.text = context!!.getString(
            R.string.coin_price,
            coin.getPriceUsd()!!.toDouble()
        )
        coinViewHolder.textViewPercentChange.text = context!!.getString(
            R.string.coin_percent_change,
            coin.getChangePercent24Hr()!!.toDouble()
        )

        if (coin.getChangePercent24Hr()!!.toDouble() >= 0) {
            coinViewHolder.textViewPercentChange.setTextColor(
                context!!.resources.getColor(R.color.percentChangePositive)
            )
        } else {
            coinViewHolder.textViewPercentChange.setTextColor(
                context!!.resources.getColor(R.color.percentChangeNegative)
            )
        }

        coinViewHolder.itemView.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                listener!!.onCoinClick(coin)
            }
        });


    }

    override fun getItemCount(): Int {
        return if (null != coinList) coinList!!.size else 0
    }

    inner class CoinViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val textViewId: TextView
        val textViewName: TextView
        val textViewSymbol: TextView
        val textViewPrice: TextView
        val textViewPercentChange: TextView

        init {
            context = itemView.context
            textViewId = itemView.findViewById(R.id.coin_id)
            textViewName = itemView.findViewById(R.id.coin_name)
            textViewPrice = itemView.findViewById(R.id.coin_price)
            textViewSymbol = itemView.findViewById(R.id.coin_symbol)
            textViewPercentChange = itemView.findViewById(R.id.coin_percent_change)
        }
    }

    //из вне передаем наш список крипты
    //каждый раз когда будем вызывать сет, то будем обновлять список
    // и адаптер будет перерисовываь список
    fun setCoins(coinList: List<Coin>?) {
        this.coinList = coinList
        notifyDataSetChanged()
    }

    fun setOnCoinClickListener(listener: OnCoinClickListener?) {
        this.listener = listener
    }

}