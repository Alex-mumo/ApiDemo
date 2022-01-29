package com.example.apidemo.data.network

import com.example.apidemo.data.local.model.Quote
import retrofit2.Call
import retrofit2.http.GET

interface QuoteApi {

    @GET("/v1/quotes/")
    fun getAllQuotes(): Call<List<Quote>>
}