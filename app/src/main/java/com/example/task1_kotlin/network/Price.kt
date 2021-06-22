package com.example.task1_kotlin.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import android.os.Parcel
import android.os.Parcelable

public class Price(

    @SerializedName("priceUsd")
    @Expose
    private var priceUsd: String? = null,

    @SerializedName("time")
    @Expose
    private var time: String? = null
): Parcelable {

    constructor(parcel: Parcel) : this() {
        priceUsd = parcel.readString()
        time = parcel.readString()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<Price> {
        override fun createFromParcel(parcel: Parcel): Price {
            return Price(parcel)
        }

        override fun newArray(size: Int): Array<Price?> {
            return arrayOfNulls(size)
        }
    }

    fun getPriceUsd(): String? {
        return priceUsd
    }

    fun setPriceUsd(priceUsd: String?) {
        this.priceUsd = priceUsd
    }

    fun getTime(): String? {
        return time
    }

    fun setTime(time: String?) {
        this.time = time
    }


}