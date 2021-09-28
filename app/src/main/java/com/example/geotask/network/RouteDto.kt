package com.example.geotask.network

import com.google.gson.annotations.SerializedName

data class RouteDto(
    @SerializedName ("routes")
    val legs: List<Leg>
)

data class Leg (
    @SerializedName("end_location")
    val endLocation: Northeast,
    @SerializedName("start_location")
    val startLocation: Northeast,
)

data class Northeast (
    @SerializedName ("lat")
    val lat: Double,
    @SerializedName ("lng")
    val lng: Double
)
