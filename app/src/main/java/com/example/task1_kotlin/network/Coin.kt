package com.example.task1_kotlin.network

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

public class Coin(

    @SerializedName("id")
    @Expose
    private var id: String? = null,

    @SerializedName("rank")
    @Expose
    private var rank: String? = null,

    @SerializedName("symbol")
    @Expose
    private var symbol: String? = null,

    @SerializedName("name")
    @Expose
    private var name: String? = null,

    @SerializedName("supply")
    @Expose
    private var supply: String? = null,

    @SerializedName("maxSupply")
    @Expose
    private var maxSupply: String? = null,

    @SerializedName("marketCapUsd")
    @Expose
    private var marketCapUsd: String? = null,

    @SerializedName("volumeUsd24Hr")
    @Expose
    private var volumeUsd24Hr: String? = null,

    @SerializedName("priceUsd")
    @Expose
    private var priceUsd: String? = null,

    @SerializedName("changePercent24Hr")
    @Expose
    private var changePercent24Hr: String? = null,

    @SerializedName("vwap24Hr")
    @Expose
    private var vwap24Hr: String? = null,

    @SerializedName("explorer")
    @Expose
    private var explorer: String? = null

): Parcelable {

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        rank = parcel.readString()
        symbol = parcel.readString()
        name = parcel.readString()
        supply = parcel.readString()
        maxSupply = parcel.readString()
        marketCapUsd = parcel.readString()
        volumeUsd24Hr = parcel.readString()
        priceUsd = parcel.readString()
        changePercent24Hr = parcel.readString()
        vwap24Hr = parcel.readString()
        explorer = parcel.readString()
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getRank(): String? {
        return rank
    }

    fun setRank(rank: String?) {
        this.rank = rank
    }

    fun getSymbol(): String? {
        return symbol
    }

    fun setSymbol(symbol: String?) {
        this.symbol = symbol
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getSupply(): String? {
        return supply
    }

    fun setSupply(supply: String?) {
        this.supply = supply
    }

    fun getMaxSupply(): String? {
        return maxSupply
    }

    fun setMaxSupply(maxSupply: String?) {
        this.maxSupply = maxSupply
    }

    fun getMarketCapUsd(): String? {
        return marketCapUsd
    }

    fun setMarketCapUsd(marketCapUsd: String?) {
        this.marketCapUsd = marketCapUsd
    }

    fun getVolumeUsd24Hr(): String? {
        return volumeUsd24Hr
    }

    fun setVolumeUsd24Hr(volumeUsd24Hr: String?) {
        this.volumeUsd24Hr = volumeUsd24Hr
    }

    fun getPriceUsd(): String? {
        return priceUsd
    }

    fun setPriceUsd(priceUsd: String?) {
        this.priceUsd = priceUsd
    }

    fun getChangePercent24Hr(): String? {
        return changePercent24Hr
    }

    fun setChangePercent24Hr(changePercent24Hr: String?) {
        this.changePercent24Hr = changePercent24Hr
    }

    fun getVwap24Hr(): String? {
        return vwap24Hr
    }

    fun setVwap24Hr(vwap24Hr: String?) {
        this.vwap24Hr = vwap24Hr
    }

    fun getExplorer(): String? {
        return explorer
    }

    fun setExplorer(explorer: String?) {
        this.explorer = explorer
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(name)
        dest.writeString(symbol)
        dest.writeString(rank)
        dest.writeString(priceUsd)
        dest.writeString(marketCapUsd)
        dest.writeString(supply)
        dest.writeString(maxSupply)
        dest.writeString(volumeUsd24Hr)
        dest.writeString(changePercent24Hr)
        dest.writeString(vwap24Hr)
        dest.writeString(explorer)
    }
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Coin> {
        override fun createFromParcel(parcel: Parcel): Coin {
            return Coin(parcel)
        }

        override fun newArray(size: Int): Array<Coin?> {
            return arrayOfNulls(size)
        }
    }
}
