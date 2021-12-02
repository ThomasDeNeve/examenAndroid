package com.example.hier.network

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder
{
    private val client = OkHttpClient.Builder().build()

    //private val url = "https://localhost:5001"

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5001/") // change this IP for testing by your actual machine IP
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}