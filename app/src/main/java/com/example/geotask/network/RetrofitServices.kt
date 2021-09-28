package com.example.geotask.network

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {
    @GET("sensor=false")
    fun getRoutesList(@Body request: EntryRequest): Call<MutableList<RouteDto>>
}

data class EntryRequest(
    @SerializedName("origin")
    val origin: String,
    @SerializedName("destination")
    val destination: String
)