package com.example.apidemo.data.network

import com.example.apidemo.data.local.model.Quote
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApi {
    @GET("random")
    suspend fun getAllQuotes(): Response<List<Quote>>
}