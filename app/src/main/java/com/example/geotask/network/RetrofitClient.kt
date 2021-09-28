package com.example.geotask.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient : RetrofitServices {

    companion object {
        private const val BASE_URL = ""
    }

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun getRoutesList(request: EntryRequest): Call<MutableList<RouteDto>> {
        return retrofit().create(RetrofitServices::class.java).getRoutesList(request)
    }
}